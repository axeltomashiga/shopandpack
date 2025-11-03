package com.tpo.shopandpack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_stickers")
public class UserSticker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "sticker_id", nullable = false)
    private Sticker sticker;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    @Column(name = "created_at")
    @Setter(lombok.AccessLevel.NONE) // CreatedAt es inmutable
    private LocalDateTime createdAt;
    
    public enum Estado {
        EN_COLECCION, EN_TRADE
    }
    
    // Constructor por defecto
    public UserSticker() {
        this.createdAt = LocalDateTime.now();
        this.estado = Estado.EN_COLECCION;
    }
    
    // Constructor
    public UserSticker(User user, Sticker sticker) {
        this();
        this.user = user;
        this.sticker = sticker;
    }
    
}