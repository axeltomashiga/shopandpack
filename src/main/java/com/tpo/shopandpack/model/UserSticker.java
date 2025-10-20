package com.tpo.shopandpack.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
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
    private LocalDateTime createdAt;
    
    public enum Estado {
        EN_COLECCION, EN_TRADE
    }
}
