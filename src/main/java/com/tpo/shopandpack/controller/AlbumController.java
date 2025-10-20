package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    /**
     * GET /api/albums
     * GET /api/albums?publicado=true
     * GET /api/albums?categoria=deportes
     * GET /api/albums?publicado=true&categoria=deportes
     */
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(
            @RequestParam(required = false) Boolean publicado,
            @RequestParam(required = false) String categoria) {
        
        // Si hay filtros específicos, aplicarlos
        if (categoria != null) {
            List<Album> albums = albumService.findByCategoria(categoria);
            // Si también hay filtro de publicado, filtrar en memoria
            if (publicado != null) {
                albums = albums.stream()
                        .filter(a -> publicado.equals(a.getPublicado()))
                        .toList();
            }
            return ResponseEntity.ok(albums);
        }
        
        if (publicado != null) {
            List<Album> albums = albumService.findByPublicado(publicado);
            return ResponseEntity.ok(albums);
        }
        
        // Sin filtros, devolver todos
        List<Album> albums = albumService.findAll();
        return ResponseEntity.ok(albums);
    }
    
    /**
     * GET /api/albums/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumService.findById(id);
        return ResponseEntity.ok(album);
    }
    
    /**
     * POST /api/albums
     */
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album savedAlbum = albumService.save(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum);
    }
    
    /**
     * PUT /api/albums/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album updatedAlbum = albumService.update(id, album);
        return ResponseEntity.ok(updatedAlbum);
    }
    
    /**
     * DELETE /api/albums/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

