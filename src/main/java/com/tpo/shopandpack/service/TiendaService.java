package com.tpo.shopandpack.service;

import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.repository.AlbumRepository;
import com.tpo.shopandpack.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de lógica de negocio para operaciones de la tienda.
 * Este servicio orquesta operaciones complejas que involucran múltiples entidades.
 * Para operaciones CRUD simples sobre Pack, usar PackService.
 */
@Service
@Transactional
public class TiendaService {
    
    @Autowired
    private PackageRepository packRepository;
    
    @Autowired
    private AlbumRepository albumRepository;
    
    /**
     * Obtiene todos los packs de la tienda (para vista administrativa o histórico).
     */
    public List<Pack> getAllPacks() {
        return packRepository.findAll();
    }
    
    /**
     * Obtiene un pack específico por ID.
     */
    public Pack getPackById(Long id) {
        return packRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pack", "id", id));
    }
    
    /**
     * Obtiene todos los álbumes disponibles en la tienda.
     */
    public List<Album> getAlbumsDisponibles() {
        return albumRepository.findByPublicado(true);
    }
    
    /**
     * Obtiene un álbum por ID (solo si está publicado).
     */
    public Album getAlbumById(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new ResourceNotFoundException("Album", "id", albumId));
        
        if (!album.getPublicado()) {
            throw new ResourceNotFoundException("Album publicado", "id", albumId);
        }
        
        return album;
    }
    
    // Métodos futuros para lógica de compra:
    // public CompraResult comprarPaquete(Long userId, Long albumId) { ... }
    // public List<Pack> getPacksByUser(Long userId) { ... }
    // public EstadisticasCompra getEstadisticasUsuario(Long userId) { ... }
}
