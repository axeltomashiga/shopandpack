package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.dto.PackDTO;
import com.tpo.shopandpack.dto.StickerDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
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
        @RequestParam(required = true) Long userId,
        @RequestParam(defaultValue = "efectivo") String metodoPago
    ){
        PackDTO pack = tiendaService.comprarPaquete(albumId, userId, metodoPago);
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

    /**
     * Endpoint para todos los stickers disponibles
     * @return lista de stickers
     */
    @GetMapping("/albums/stickers/all")
    public ResponseEntity<?> obtenerStickers() {
        List<StickerDTO> stickers = tiendaService.obtenerStickers();

        Map<String, Object> response = new HashMap<>();
        response.put("stickers", stickers);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint para obtener los stickers de un usuario en un álbum específico
     * @param userId ID del usuario
     * @param albumId ID del álbum (opcional)
     * @return lista de stickers del usuario en el álbum
     */
    @GetMapping("/albums/stickers")
    public ResponseEntity<?> obtenerStickersUserAlbum(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) Long albumId) {

        List<StickerDTO> stickers = tiendaService.obtenerStickersUserAlbum(userId, albumId);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("albumId", albumId);
        response.put("stickers", stickers);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint para obtener los stickers de un usuario en todos sus álbumes
     * @variable userId ID del usuario
     * @return lista de stickers del usuario
     */
    @GetMapping("/albums/stickers/user/{userId}")
    public ResponseEntity<?> obtenerStickersUser(
            @PathVariable Long userId) {

        List<StickerDTO> stickers = tiendaService.obtenerStickersUserAlbum(userId, null);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("stickers", stickers);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Endpoint para obtener los álbumes de un usuario
     * @variable userId ID del usuario
     * @return lista de álbumes del usuario
     */
    @GetMapping("/albums/user/{userId}")
    public ResponseEntity<?> obtenerAlbumsDeUser(
            @PathVariable Long userId) {

        List<?> albums = tiendaService.obtenerAlbumsDeUser(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("albums", albums);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}