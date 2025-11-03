package com.tpo.shopandpack.Adapter;
import com.tpo.shopandpack.model.Pack;

public class PagoEfectivo implements ITipoPago {

    @Override
    public void procesarPago(Pack pack) {
        // Lógica para procesar el pago en efectivo
        System.out.println("Procesando pago en efectivo para el pack ID: " + pack.getId());
    }
}
