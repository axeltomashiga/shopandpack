package com.tpo.shopandpack.emun;

import com.tpo.shopandpack.Adapter.ITipoPago;
import com.tpo.shopandpack.Adapter.PagoEfectivo;
import com.tpo.shopandpack.Adapter.PagoTarjeta;

public enum TipoPago {
    TARJETA_CREDITO,
    DEBITO,
    PAYPAL,
    EFECTIVO;

    public ITipoPago getTipoPago() {
        return switch (this) {
            case TARJETA_CREDITO -> new PagoTarjeta();
            case DEBITO -> new PagoTarjeta();
            case PAYPAL -> new PagoTarjeta();
            case EFECTIVO -> new PagoEfectivo();
        };
    }
}
