package com.tpo.shopandpack.model.Adapter;

public class PagoMercadoPago implements IPagoAdapter {

    @Override
    public void procesarPago(Double monto) {
        System.out.println("Procesando pago de " + monto + " a través de Mercado Pago.");
    }
}
