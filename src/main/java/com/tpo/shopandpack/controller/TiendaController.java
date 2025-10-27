package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.decorator.PackPromo;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.dto.ComprarPackRequestDTO;
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

    @GetMapping("/albums/{albumId}/packs")
    public ResponseEntity<?> comprarPaquete(@PathVariable(required = true) Long albumId, @RequestBody ComprarPackRequestDTO request){
        PackDTO pack = tiendaService.comprarPaquete(albumId, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(pack);
    }

    @GetMapping("/albums/{albumId}/price")
    public ResponseEntity<Double> obtenerPrecio(){
        Double precio = tiendaService.obtenerPrecio();
        return ResponseEntity.status(HttpStatus.OK).body(precio);
    }
    
    /**
     * Endpoint para comprar un pack con promoción/descuento
     * Utiliza el patrón Decorator (PackPromo)
     * 
     * @param albumId ID del álbum
     * @param request Datos de la compra (userId)
     * @param descuento Porcentaje de descuento (query param, ej: ?descuento=20)
     * @return Información del pack con descuento aplicado
     */
    @PostMapping("/albums/{albumId}/packs/promo")
    public ResponseEntity<?> comprarPaqueteConPromocion(
            @PathVariable(required = true) Long albumId, 
            @RequestBody ComprarPackRequestDTO request,
            @RequestParam(defaultValue = "0") int descuento) {
        
        if (descuento > 0) {
            // Usar el decorador PackPromo para aplicar descuento
            PackPromo packPromo = tiendaService.comprarPaqueteConPromocion(albumId, request.getUserId(), descuento);
            
            // Crear respuesta con información del pack y descuento
            Map<String, Object> response = new HashMap<>();
            response.put("packId", packPromo.getPackOriginal().getId());
            response.put("precioOriginal", packPromo.getPackOriginal().getPrecio());
            response.put("descuento", packPromo.getDescuentoPorcentaje() + "%");
            response.put("montoDescuento", packPromo.getMontoDescuento());
            response.put("precioFinal", packPromo.getPrecio());
            response.put("stickers", packPromo.getStickers());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            // Sin descuento, compra normal
            Pack pack = tiendaService.comprarPaquete(albumId, request.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED).body(pack);
        }
    }
    
    /**
     * Endpoint para obtener el precio de un pack con descuento aplicado
     * Demuestra el uso del patrón Decorator sin modificar el pack original
     * 
     * @param packId ID del pack
     * @param descuento Porcentaje de descuento (query param)
     * @return Información de precios
     */
    @GetMapping("/packs/{packId}/precio-con-descuento")
    public ResponseEntity<?> obtenerPrecioConDescuento(
            @PathVariable Long packId,
            @RequestParam(defaultValue = "0") int descuento) {
        
        Double precioConDescuento = tiendaService.obtenerPrecioConDescuento(packId, descuento);
        
        Map<String, Object> response = new HashMap<>();
        response.put("packId", packId);
        response.put("descuento", descuento + "%");
        response.put("precioConDescuento", precioConDescuento);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}