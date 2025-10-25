package com.tpo.shopandpack.service;

import com.tpo.shopandpack.decorator.PackPromo;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.repository.AlbumRepository;
import com.tpo.shopandpack.repository.PackageRepository;
import com.tpo.shopandpack.repository.UserRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.exepcion.BadRequestException;
import com.tpo.shopandpack.exepcion.NotStickersAvailable;
import com.tpo.shopandpack.emun.Estrategia;
import com.tpo.shopandpack.Strategy.IArmadoPackStrategy;
import com.tpo.shopandpack.FactoryPack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private StickerRepository stickerRepository;

    public Pack comprarPaquete(Long albumId, Long userId) {

       User user = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("Usuario no encontrado"));

       Album album = albumRepository.findById(albumId).orElseThrow(() -> new BadRequestException("Album no encontrado"));
        

       List<Sticker> stickersDisponibles = stickerRepository.findAvailableByAlbumId(albumId);
        if(stickersDisponibles.size() < 5) {
            throw new NotStickersAvailable("No hay suficientes figuritas disponibles para este album");
        }

        long albumPacksPurchasesByUser = packageRepository.countByUserIdAndAlbumId(userId, albumId);
        IArmadoPackStrategy createPackStrategy = null;

        if (albumPacksPurchasesByUser >= 40) {
            createPackStrategy = FactoryPack.getEstrategia(Estrategia.WEIGHTED);
        } else {
            createPackStrategy = FactoryPack.getEstrategia(Estrategia.UNIFORM);
        }

        List<Sticker> stickers = createPackStrategy.armarPack(stickersDisponibles);
        
        // Crear el pack con los stickers seleccionados
        Pack pack = new Pack(user, album);
        pack.crear(); // Inicializar el pack
        
        // Agregar los stickers al pack
        for (Sticker sticker : stickers) {
            pack.agregarSticker(sticker);
            sticker.reducirStock(1); // Reducir el stock del sticker
        }
        
        // Guardar los stickers actualizados
        stickerRepository.saveAll(stickers);
        
        // Guardar y retornar el pack
        return packageRepository.save(pack);
    }
    
    /**
     * Comprar un paquete con promoción/descuento
     * Utiliza el patrón Decorator para aplicar descuentos
     * 
     * @param albumId ID del álbum
     * @param userId ID del usuario
     * @param descuento Porcentaje de descuento (ej: 20 para 20%)
     * @return PackPromo con el descuento aplicado
     */
    public PackPromo comprarPaqueteConPromocion(Long albumId, Long userId, int descuento) {
        // Primero compramos el pack normal
        Pack pack = comprarPaquete(albumId, userId);
        
        // Luego lo decoramos con el descuento
        return new PackPromo(pack, descuento);
    }

    public Double obtenerPrecio() {
        return 9.99;
    }
    
    /**
     * Obtener el precio de un pack con descuento aplicado
     * 
     * @param packId ID del pack
     * @param descuento Porcentaje de descuento
     * @return Precio con descuento
     */
    public Double obtenerPrecioConDescuento(Long packId, int descuento) {
        Pack pack = packageRepository.findById(packId)
                .orElseThrow(() -> new BadRequestException("Pack no encontrado"));
        
        PackPromo packPromo = new PackPromo(pack, descuento);
        return packPromo.getPrecio();
    }
}
