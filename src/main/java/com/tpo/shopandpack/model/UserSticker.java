package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_stickers")
public class UserSticker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "sticker_id", nullable = false)
    private Sticker sticker;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public enum Estado {
        EN_COLECCION, EN_TRADE
    }
    
    // Constructor por defecto
    public UserSticker() {
        this.createdAt = LocalDateTime.now();
        this.estado = Estado.EN_COLECCION;
    }
    
    // Constructor
    public UserSticker(User user, Sticker sticker) {
        this();
        this.user = user;
        this.sticker = sticker;
    }
    
    // Método de negocio para cambiar estado
    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Sticker getSticker() { return sticker; }
    public void setSticker(Sticker sticker) { this.sticker = sticker; }
    
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSticker that = (UserSticker) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}