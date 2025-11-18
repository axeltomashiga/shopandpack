package com.tpo.shopandpack.adapter;

import org.springframework.stereotype.Component;

import com.tpo.shopandpack.model.Pack;
@Component("efectivo")
public class PagoEfectivo implements IPago {
    @Override
    public void pagar(Pack pack) {
        System.out.println("Procesando pago en efectivo para el pack ID: " + pack.getId());
    }
}
