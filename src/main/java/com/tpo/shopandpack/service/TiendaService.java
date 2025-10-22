package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TiendaService {

    public Pack comprarPaquete(Long albumId) {
        Pack pack = new Pack();
        pack.setId(1L);
        return pack;
    }

    public Double obtenerPrecio() {
        return 9.99;
    }
}
