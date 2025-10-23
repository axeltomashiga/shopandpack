package com.tpo.shopandpack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tpo.shopandpack.model.Strategy.IProbabilidadStickerStrategy;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;

@Entity
@Data
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
    @JsonIgnoreProperties("album")
    private List<Sticker> stickers = new ArrayList<>();

    @Transient
    private IProbabilidadStickerStrategy estrategiaProbabilidad;
    
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

    public IProbabilidadStickerStrategy getEstrategiaProbabilidad() {
        return estrategiaProbabilidad;
    }

    public void setEstrategiaProbabilidad(IProbabilidadStickerStrategy estrategiaProbabilidad) {
        this.estrategiaProbabilidad = estrategiaProbabilidad;
    }

    // Método de negocio para agregar figurita al paquete
    public void agregarSticker() {
        if (this.album != null && this.estrategiaProbabilidad != null) {
            this.stickers.addAll(estrategiaProbabilidad.listarStickers(this.album));
        } else if (this.estrategiaProbabilidad != null) {
            // Fallback al método original si no hay álbum
            this.stickers.addAll(estrategiaProbabilidad.listarStickers());
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

    public void reducirStockStickers() {
        this.stickers.forEach(sticker -> sticker.reducirStock(1));
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
