package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    
    // Método de negocio para obtener el precio (componente base del patrón Decorator)
    public Double getPrecio() {
        return this.precio;
    }
    
    // Método para establecer el precio
    public void setPrecio(Double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }
    
    /**
     * Implementación del método crear() de la interfaz IPack
     * Inicializa el pack con valores por defecto si es necesario
     */
    @Override
    public void crear() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.precio == null) {
            this.precio = 100.00;
        }
        if (this.stickers == null) {
            this.stickers = new ArrayList<>();
        }
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
