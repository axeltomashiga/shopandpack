package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.dto.PackDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import com.tpo.shopandpack.service.TiendaService;

@RestController
@RequestMapping("/api/shop")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    /**
    * Endpoint para comprar un paquete de stickers para un álbum específico
    * @param albumId ID del álbum
    * @param request Objeto que contiene el ID del usuario
    * @return Detalles del pack comprado
    */
    @PostMapping("/albums/{albumId}/packs")
    public ResponseEntity<?> comprarPaquete(
        @PathVariable(required = true) Long albumId, 
        @RequestParam(required = true) Long userId
    ){
        PackDTO pack = tiendaService.comprarPaquete(albumId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(pack);
    }

    /**
     * Endpoint para obtener el precio de un pack con descuento aplicado si corresponde
     * @param packId ID del pack
     * @param descuento Porcentaje de descuento (query param)
     * @return Información de precios
     */
    @GetMapping("/albums/{packId}/price")
    public ResponseEntity<?> obtenerPrecio(
            @PathVariable Long packId,
            @RequestParam(defaultValue = "0") int descuento) {
        
        Double precio = tiendaService.obtenerPrecio(packId, descuento);
        
        Map<String, Object> response = new HashMap<>();
        response.put("packId", packId);
        response.put("descuentoAplicado", descuento + "%");
        response.put("precioFinal", precio);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}