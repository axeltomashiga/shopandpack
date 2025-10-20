package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, updatable = false)
    @Setter(lombok.AccessLevel.NONE) // Username es inmutable
    private String username;
    
    @Column(nullable = false)
    private String email;
    
    private String telefono;
    private String nombre;
    private String apellido;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String hashPassword;
    private String avatarUrl;
    
    @Column(name = "created_at")
    @Setter(lombok.AccessLevel.NONE) // CreatedAt es inmutable
    private LocalDateTime createdAt;
    
    public enum Role {
        ADMIN, USER
    }
    
    // Constructor por defecto requerido por JPA
    public User() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Constructor
    public User(String username, String email, String nombre, String apellido, Role role) {
        this();
        this.username = username;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.role = role;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
