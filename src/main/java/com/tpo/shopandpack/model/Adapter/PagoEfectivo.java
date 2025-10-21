package com.tpo.shopandpack.model.Adapter;

public class PagoEfectivo implements IPagoAdapter {
    @Override
    public void procesarPago(Double monto) {
        // Lógica para procesar el pago en efectivo
        System.out.println("Procesando pago en efectivo por monto: " + monto);
    }
    
}
