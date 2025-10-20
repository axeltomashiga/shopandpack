package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "packs")
public class Pack {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToMany
    @JoinTable(
        name = "pack_stickers",
        joinColumns = @JoinColumn(name = "pack_id"),
        inverseJoinColumns = @JoinColumn(name = "sticker_id")
    )
    private List<Sticker> stickers = new ArrayList<>();
    
    // Constructor por defecto
    public Pack() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Constructor
    public Pack(User user, Album album) {
        this();
        this.user = user;
        this.album = album;
    }
    
    // Método de negocio para agregar figurita al paquete
    public void agregarSticker(Sticker sticker) {
        if (stickers.size() < 5) { // Máximo 5 figuritas por paquete
            stickers.add(sticker);
        } else {
            throw new IllegalStateException("El paquete ya tiene 5 figuritas");
        }
    }
    
    // Método para verificar si el paquete está completo
    public boolean estaCompleto() {
        return stickers.size() == 5;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public List<Sticker> getStickers() { return new ArrayList<>(stickers); }
    public void setStickers(List<Sticker> stickers) { this.stickers = stickers; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return Objects.equals(id, pack.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
