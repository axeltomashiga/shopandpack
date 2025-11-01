package com.tpo.shopandpack.service;

import com.tpo.shopandpack.decorator.PackPromo;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.model.UserSticker;
import com.tpo.shopandpack.repository.AlbumRepository;
import com.tpo.shopandpack.repository.PackageRepository;
import com.tpo.shopandpack.repository.UserRepository;
import com.tpo.shopandpack.repository.UserStickerRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.exepcion.BadRequestException;
import com.tpo.shopandpack.exepcion.NotStickersAvailable;
import com.tpo.shopandpack.emun.Estrategia;
import com.tpo.shopandpack.emun.TipoPago;
import com.tpo.shopandpack.Strategy.IStickerSelectionStrategy;
import com.tpo.shopandpack.FactoryPack;
import com.tpo.shopandpack.dto.ComprarPackRequestDTO;
import com.tpo.shopandpack.dto.PackDTO;

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

    @Autowired
    private UserStickerRepository userStickerRepository;

    @Autowired
    private PagoService pagoService;
    
    
    public PackDTO comprarPaquete(Long albumId, Long userId, TipoPago tipoPago) {

        User user = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("Usuario no encontrado"));

        Album album = albumRepository.findById(albumId).orElseThrow(() -> new BadRequestException("Album no encontrado"));

        List<Sticker> stickersDisponibles = stickerRepository.findAvailableByAlbumId(albumId);
        if (stickersDisponibles.size() < 5) {
            throw new NotStickersAvailable("No hay suficientes figuritas disponibles para este album");
        }

        long albumPacksPurchasesByUser = packageRepository.countByUserIdAndAlbumId(userId, albumId);

        IStickerSelectionStrategy stickersSelectionStrategy = null;
        if (albumPacksPurchasesByUser >= 40) {
            stickersSelectionStrategy = FactoryPack.getEstrategia(Estrategia.WEIGHTED);
        } else {
            stickersSelectionStrategy = FactoryPack.getEstrategia(Estrategia.UNIFORM);
        }

        List<Sticker> stickers = stickersSelectionStrategy.selectStickers(stickersDisponibles);
        
        Pack pack = new Pack(user, album);
        pack.crear();
        pack.setStickers(stickers);

        // 1. PRIMERO: Guardar el pack para que tenga ID
        packageRepository.save(pack);
        
        // 2. SEGUNDO: Procesar el pago (ahora el pack ya tiene ID)
        //pagoService.procesarPago(pack, request);
        
        // 3. TERCERO: Guardar stickers y relaciones
        stickerRepository.saveAll(stickers);

        userStickerRepository.saveAll(
            stickers.stream()
                .map(sticker -> new UserSticker(user, sticker))
                .toList()
        );

        PackDTO packDTO = new PackDTO(pack);
        return packDTO;
    }
 
    /**
     * Obtener el precio de un pack con descuento aplicado
     * 
     * @param packId ID del pack
     * @param descuento Porcentaje de descuento
     * @return Precio con descuento
     */
    public Double obtenerPrecio(Long packId, int descuento) {
        Pack pack = packageRepository.findById(packId)
                .orElseThrow(() -> new BadRequestException("Pack no encontrado"));
        
        PackPromo packPromo = new PackPromo(pack, descuento);
        return packPromo.getPrecio();
    }
}
