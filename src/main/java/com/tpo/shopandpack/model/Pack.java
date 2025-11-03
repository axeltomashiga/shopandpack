package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "packs")
public class Pack implements IPack {
    
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
    
    @Column(nullable = false)
    private Double precio;
    
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
        this.precio = 100.00; // Precio base por defecto
    }
    
    // Constructor
    public Pack(User user, Album album) {
        this();
        this.user = user;
        this.album = album;
    }
    
    // Constructor con precio
    public Pack(User user, Album album, Double precio) {
        this();
        this.user = user;
        this.album = album;
        this.precio = precio;
    }

    // Método para establecer el precio
    public void setPrecio(Double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }
}
