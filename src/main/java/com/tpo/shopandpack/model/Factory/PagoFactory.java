package com.tpo.shopandpack.model.Factory;

import org.springframework.stereotype.Component;
import com.tpo.shopandpack.model.Adapter.IPagoAdapter;
import com.tpo.shopandpack.model.Adapter.PagoEfectivo;
import com.tpo.shopandpack.model.Adapter.PagoMercadoPago;
import com.tpo.shopandpack.model.Adapter.PagoTC;


@Component
public class PagoFactory {
    public static IPagoAdapter crearMetodoPago(String tipoPago) {
        switch (tipoPago) {
            case "TARJETA_CREDITO":
                return new PagoTC();
            case "EFECTIVO":
                return new PagoEfectivo();
            case "MERCADO_PAGO":
                return new PagoMercadoPago();
            default:
                throw new IllegalArgumentException("Tipo de pago no soportado: " + tipoPago);
        }
    }
}
