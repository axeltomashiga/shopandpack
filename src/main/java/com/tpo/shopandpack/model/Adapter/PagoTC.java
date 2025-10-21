package com.tpo.shopandpack.model.Adapter;

public class PagoTC implements IPagoAdapter {
    @Override
    public void procesarPago(Double monto) {
        // Lógica para procesar el pago con tarjeta de crédito
        System.out.println("Procesando pago con tarjeta de crédito por monto: " + monto);
    }
    
}
