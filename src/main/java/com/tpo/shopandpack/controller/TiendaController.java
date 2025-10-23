package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.dto.ComprarPackRequestDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.tpo.shopandpack.service.TiendaService;

@RestController
@RequestMapping("/api/shop")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping("/albums/{albumId}/packs")
    public ResponseEntity<?> comprarPaquete(@PathVariable(required = true) Long albumId, @RequestBody ComprarPackRequestDTO request){
        Pack pack = tiendaService.comprarPaquete(albumId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(pack);
    }

    @GetMapping("/albums/{albumId}/price")
    public ResponseEntity<Double> obtenerPrecio(){
        Double precio = tiendaService.obtenerPrecio();
        return ResponseEntity.status(HttpStatus.OK).body(precio);
    }
}