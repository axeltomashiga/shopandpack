package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "albums")
public class Album {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;
    
    private String descripcion;
    private String categoria;
    
    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;
    
    @ManyToOne
    @JoinColumn(name = "creador_admin_id")
    private User creadorAdmin;
    
    private Integer totalFiguritas;
    private Boolean publicado = false;
    
    @Column(name = "created_at")
    @Setter(lombok.AccessLevel.NONE) // CreatedAt es inmutable
    private LocalDateTime createdAt;

    public enum Dificultad {
        FACIL, MEDIO, DIFICIL
    }

    // Constructor por defecto
    public Album() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Constructor
    public Album(String titulo, String descripcion, String categoria, 
                 Dificultad dificultad, User creadorAdmin, Integer totalFiguritas) {
        this();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.dificultad = dificultad;
        this.creadorAdmin = creadorAdmin;
        this.totalFiguritas = totalFiguritas;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(id, album.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}