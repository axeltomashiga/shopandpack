package com.tpo.shopandpack.service;

import com.tpo.shopandpack.dto.PackDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TiendaService {
    public String comprarPaquete() {

        return "packete comprado";
    }
}
