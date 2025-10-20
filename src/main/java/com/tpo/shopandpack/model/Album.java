package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
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
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sticker> stickers = new ArrayList<>();
    
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
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    
    public Dificultad getDificultad() { return dificultad; }
    public void setDificultad(Dificultad dificultad) { this.dificultad = dificultad; }
    
    public User getCreadorAdmin() { return creadorAdmin; }
    public void setCreadorAdmin(User creadorAdmin) { this.creadorAdmin = creadorAdmin; }
    
    public Integer getTotalFiguritas() { return totalFiguritas; }
    public void setTotalFiguritas(Integer totalFiguritas) { this.totalFiguritas = totalFiguritas; }
    
    public Boolean getPublicado() { return publicado; }
    public void setPublicado(Boolean publicado) { this.publicado = publicado; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    public List<Sticker> getStickers() { return stickers; }
    public void setStickers(List<Sticker> stickers) { this.stickers = stickers; }
    
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