package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.dto.PackDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.tpo.shopandpack.service.TiendaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/shop")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping("/albums/{id}/packs")
    public ResponseEntity<String> comprarPaquete(){
        String pack = tiendaService.comprarPaquete();
        return ResponseEntity.status(HttpStatus.CREATED).body(pack);

    }

}