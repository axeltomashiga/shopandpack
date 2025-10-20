package com.tpo.shopandpack.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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

    public enum Dificultad {
        FACIL, MEDIO, DIFICIL
    }

    @ManyToOne
    @JoinColumn(name = "creador_admin_id")
    private User creadorAdmin;

    private Integer totalFiguritas;
    private Boolean publicado = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
