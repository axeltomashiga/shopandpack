package com.tpo.shopandpack.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
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
    private LocalDateTime createdAt;

    public enum Role {
        ADMIN, USER
    }
}
