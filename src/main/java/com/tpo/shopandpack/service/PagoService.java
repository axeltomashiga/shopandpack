package com.tpo.shopandpack.service;

import org.springframework.stereotype.Service;

import com.tpo.shopandpack.dto.CompraDTO;
import com.tpo.shopandpack.model.Pago;


@Service
public class PagoService {

    public void procesarPago(CompraDTO compraDTO) {
        Pago pago = new Pago(compraDTO.getMetodoPago());
        try {
            pago.procesarPago(compraDTO.getMontoTotal());
        } catch (Exception e) {
            throw new RuntimeException("Error procesando el pago: " + e.getMessage());
        }
    }
}
