package com.tpo.shopandpack.model;

import com.tpo.shopandpack.Adapter.ITipoPago;
import com.tpo.shopandpack.emun.TipoPago;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    // Constructor vacío requerido por JPA/Hibernate
    public Pago() {}

    public Pago(Pack pack, User user, TipoPago tipoPago) {
        this.pack = pack;
        this.user = user;
        this.tipoPago = tipoPago;
          
    }

    public void procesarPago() {
        ITipoPago metodoPago = tipoPago.getTipoPago();
        metodoPago.procesarPago(this.pack);
    }

}
