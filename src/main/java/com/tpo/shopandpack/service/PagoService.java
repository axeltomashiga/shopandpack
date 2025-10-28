package com.tpo.shopandpack.service;

import org.springframework.stereotype.Service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Pago;
import com.tpo.shopandpack.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    public void procesarPago(Pack pack) {
        pack.reducirStockStickers();
    }
    public void procesarPago(Pack pack, User user) {
        Pago pago = new Pago(pack, user);
        pago.procesarPago();
    }
}