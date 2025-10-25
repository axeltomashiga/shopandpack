package com.tpo.shopandpack.model;

import com.tpo.shopandpack.emun.Rareza;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "stickers")
public class Sticker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;
    
    @Column(nullable = false)
    private String nombre;
    
    private Integer numero;
    
    @Enumerated(EnumType.STRING)
    private Rareza rareza;
    
    private Integer stockTotal;
    private Integer stockDisponible;
    private String imagenUrl;
    
    // Constructor por defecto
    public Sticker(Rareza rareza) {
        this.rareza = rareza;
        this.stockTotal = this.rareza.getStock();
        this.stockDisponible = stockTotal;
    }
    
    // Constructor
    public Sticker(Album album, String nombre, Integer numero, 
                   Rareza rareza, String imagenUrl) {
        this.album = album;
        this.nombre = nombre;
        this.numero = numero;
        this.rareza = rareza;
        this.stockTotal = this.rareza.getStock();
        this.stockDisponible = stockTotal;
        this.imagenUrl = imagenUrl;
    }
    
    // Método de negocio para reducir stock
    public boolean reducirStock(int cantidad) {
        if (stockDisponible >= cantidad) {
            stockDisponible -= cantidad;
            return true;
        }
        return false;
    }
    
    // Método para verificar disponibilidad
    public boolean estaDisponible() {
        return stockDisponible > 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sticker sticker = (Sticker) o;
        return Objects.equals(id, sticker.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}