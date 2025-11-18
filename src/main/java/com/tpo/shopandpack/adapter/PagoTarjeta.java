package com.tpo.shopandpack.adapter;

import org.springframework.stereotype.Component;

import com.tpo.shopandpack.model.Pack;
@Component("tarjeta")
public class PagoTarjeta implements IPago {
    @Override
    public void pagar(Pack pack) {
        System.out.println("Procesando pago con tarjeta para el pack ID: " + pack.getId());
    }

}
