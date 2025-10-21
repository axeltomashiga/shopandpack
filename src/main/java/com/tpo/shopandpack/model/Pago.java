package com.tpo.shopandpack.model;

import com.tpo.shopandpack.model.Adapter.IPagoAdapter;
import com.tpo.shopandpack.model.Factory.PagoFactory;

public class Pago {
    // Atributos
    private IPagoAdapter metodoPago;

    // Constructor
    public Pago(String tipoPago) {
        this.metodoPago = PagoFactory.crearMetodoPago(tipoPago);
    }

    // Método para procesar el pago
    public void procesarPago(Double monto) {
        this.metodoPago.procesarPago(monto);
    }
}
