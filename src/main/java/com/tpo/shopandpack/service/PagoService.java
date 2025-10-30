package com.tpo.shopandpack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpo.shopandpack.dto.ComprarPackRequestDTO;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Pago;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;
    
    public void procesarPago(Pack pack) {
        pack.reducirStockStickers();
    }
    public void procesarPago(Pack pack, ComprarPackRequestDTO request) {
        User user = pack.getUser();
        String tipoPago = request.getTipoPago();
        
        Pago pago = new Pago(pack, user, tipoPago);
        pago.procesarPago();
        pagoRepository.save(pago);
    }
}