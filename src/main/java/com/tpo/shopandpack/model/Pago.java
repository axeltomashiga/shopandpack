package com.tpo.shopandpack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;

    public Pago(Pack pack, User user) {
        this.pack = pack;
        this.user = user;
    }

    public void procesarPago() {
        this.pack.reducirStockStickers();
        System.out.println("Procesando pago para el pack ID: " + pack.getId() +
                           " del usuario: " + user.getId());
    }

}
