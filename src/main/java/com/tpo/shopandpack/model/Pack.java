package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.tpo.shopandpack.decorator.IPackDecorator;

import lombok.Data;

@Entity
@Data
@Table(name = "packs")
public class Pack implements IPackDecorator{
    
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "packs_stickers",
        joinColumns = @JoinColumn(name = "pack_id"),
        inverseJoinColumns = @JoinColumn(name = "sticker_id")
    )
    private List<Sticker> stickers = new ArrayList<>();

    // Constructor por defecto
    public Pack() {
        this.createdAt = LocalDateTime.now();
        this.precio = 3000.00;
    }
    
    // Constructor
    public Pack(User user, Album album) {
        this();
        this.user = user;
        this.album = album;
        this.precio = 3000.00;
    }
    
    // Constructor con precio
    public Pack(User user, Album album, Double precio) {
        this();
        this.user = user;
        this.album = album;
        this.precio = precio;
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
}
