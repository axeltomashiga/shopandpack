package com.tpo.shopandpack.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tpo.shopandpack.infrastructure.IPack;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "packs_especial")
public class PackEspecial implements IPack{
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
    public PackEspecial() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Constructor
    public PackEspecial(User user, Album album) {
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
    
    // Método personalizado para getStickers que mantiene la immutabilidad
    public List<Sticker> getStickers() { 
        return new ArrayList<>(stickers); 
    }
    
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

    @Override
    public String getTipo() {
        return "ESPECIAL";
    }
}
