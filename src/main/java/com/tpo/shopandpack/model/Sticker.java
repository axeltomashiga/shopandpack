package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @Column(nullable = false)
    private String nombre;

    private Integer stockTotal;
    private Integer stockDisponible;
    private String imagenUrl;

     public enum Rareza {
        COMUN(300),
        RARA(200), 
        EPICA(100);
        
        private final int stock_inicial;
        
        Rareza(int stock_inicial) {
            this.stock_inicial = stock_inicial;
        }
        
        public int getStockIncial() {
            return stock_inicial;
        }
    }
}
