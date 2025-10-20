package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para operaciones de la tienda (lógica de negocio de compras).
 * Para operaciones CRUD directas sobre entidades, usar sus controllers específicos.
 */
@RestController
@RequestMapping("/api/shop")
public class TiendaController {
    
    @Autowired
    private TiendaService tiendaService;
    
    /**
     * GET /api/shop/albums
     * Obtiene álbumes disponibles para comprar (solo publicados)
     */
    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbumsDisponibles() {
        List<Album> albums = tiendaService.getAlbumsDisponibles();
        return ResponseEntity.ok(albums);
    }
    
    /**
     * GET /api/shop/albums/{albumId}
     * Obtiene detalle de un álbum disponible (solo publicados)
     */
    @GetMapping("/albums/{albumId}")
    public ResponseEntity<Album> getAlbumDisponible(@PathVariable Long albumId) {
        Album album = tiendaService.getAlbumById(albumId);
        return ResponseEntity.ok(album);
    }
    
    /**
     * GET /api/shop/packs
     * Obtiene historial de packs comprados (vista administrativa)
     */
    @GetMapping("/packs")
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> packs = tiendaService.getAllPacks();
        return ResponseEntity.ok(packs);
    }
    
    /**
     * GET /api/shop/packs/{id}
     * Obtiene detalle de un pack específico
     */
    @GetMapping("/packs/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        Pack pack = tiendaService.getPackById(id);
        return ResponseEntity.ok(pack);
    }
    
    // Endpoint futuro para lógica de compra:
    // @PostMapping("/albums/{albumId}/comprar")
    // public ResponseEntity<Pack> comprarPack(@PathVariable Long albumId, @RequestParam Long userId) { ... }
}