package com.tpo.shopandpack.service;

import com.tpo.shopandpack.dto.CompraDTO;
import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.model.Strategy.ProbabilidadNormal;
import com.tpo.shopandpack.model.Strategy.RaroOMasAsegurada;
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
    private AlbumService albumService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PackService packService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private StickerService stickerService;
    
    @Autowired
    private ProbabilidadNormal probabilidadNormal;
    
    @Autowired
    private RaroOMasAsegurada raroOMasAsegurada;
    
    /**
     * Obtiene todos los packs de la tienda (para vista administrativa o histórico).
     */
    public List<Pack> getAllPacks() {
        return packService.findAll();
    }
    
    /**
     * Obtiene un pack específico por ID.
     */
    public Pack getPackById(Long id) {
        return packService.findById(id);
    }
    
    /**
     * Obtiene todos los álbumes disponibles en la tienda.
     */
    public List<Album> getAlbumsDisponibles() {
        return albumService.findByPublicado(true);
    }
    
    /**
     * Obtiene un álbum por ID (solo si está publicado).
     */
    public Album getAlbumById(Long albumId) {
        Album album = albumService.findById(albumId);
        if (!album.getPublicado()) {
            throw new ResourceNotFoundException("Album publicado", "id", albumId);
        }
        
        return album;
    }
    
    // Métodos futuros para lógica de compra:
    public Pack comprarPaquete(Long albumId, CompraDTO compraDTO) { 
        // 1. Validar que el álbum existe y está publicado
        Album album = getAlbumById(albumId);
        
        // 2. Obtener el usuario
        User user = userService.findById(compraDTO.getUserId());
        
        // 3. Procesar el pago ANTES de crear el pack
        // Si el pago falla, lanzará una RuntimeException automáticamente
        pagoService.procesarPago(compraDTO);
        
        // 4. Crear el pack con los datos necesarios
        Pack pack = new Pack(user, album);
        
        // 5. Asignar la estrategia correcta usando beans de Spring
        if ("premium".equals(compraDTO.getTipoPack())) {
            pack.setEstrategiaProbabilidad(raroOMasAsegurada);
        } else {
            pack.setEstrategiaProbabilidad(probabilidadNormal);
        }

        // 6. Generar stickers para el pack usando la estrategia adecuada
        pack.agregarSticker();
        
        // 7. Reducir stock de los stickers generados
        pack.reducirStockStickers();
        
        // 8. Guardar el pack
        Pack savedPack = packService.save(pack);
        System.out.println("Pack guardado exitosamente con ID: " + savedPack.getId());
        
        return savedPack;
    }
    


    
    // public List<Pack> getPacksByUser(Long userId) { ... }
    // public EstadisticasCompra getEstadisticasUsuario(Long userId) { ... }
}
