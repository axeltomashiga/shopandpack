package com.tpo.shopandpack.adapter;

import org.springframework.stereotype.Component;

import com.tpo.shopandpack.model.Pack;

 @Component("desconocido")

public class AdapterPago implements IPago {
    private PagoDesc metodoPago;

    public AdapterPago() {
        this.metodoPago = new PagoDesc();
    }

    @Override
    public void pagar(Pack pack) {
        System.out.println("Usando el adaptador de pago:");
        metodoPago.autorizar();
        metodoPago.cobrar();
        metodoPago.cerrar();
    }
}
