package com.tpo.shopandpack.Adapter;

import com.tpo.shopandpack.model.Pack;

public class PagoTarjeta implements ITipoPago {

    @Override
    public void procesarPago(Pack pack) {
        // Lógica para procesar el pago con tarjeta de crédito
        System.out.println("Procesando pago con tarjeta de crédito para el pack ID: " + pack.getId());
        pack.reducirStockStickers();
    }
}
