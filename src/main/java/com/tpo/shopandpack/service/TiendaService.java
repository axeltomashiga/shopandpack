package com.tpo.shopandpack.service;

import com.tpo.shopandpack.decorator.PackPromo;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.repository.*;
import com.tpo.shopandpack.exepcion.BadRequestException;
import com.tpo.shopandpack.exepcion.NotStickersAvailable;
import com.tpo.shopandpack.emun.Estrategia;
import com.tpo.shopandpack.Strategy.IStickerSelectionStrategy;
import com.tpo.shopandpack.FactoryPack;
import com.tpo.shopandpack.dto.AlbumDTO;
import com.tpo.shopandpack.dto.PackDTO;
import com.tpo.shopandpack.dto.StickerDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    private UserStickerRepository userStickerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private PagoService pagoService;
    
    
    public PackDTO comprarPaquete(Long albumId, Long userId) {

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
        pack.setStickers(stickers);
       
        packageRepository.save(pack);

        pagoService.procesar(pack);

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

    public List <StickerDTO> obtenerStickers() {
        return stickerRepository.findAll().stream().map(StickerDTO::new).toList();
    }

    public List<StickerDTO> obtenerStickersUserAlbum(Long userId, Long albumId) {
        List<Sticker> stickers = userStickerRepository.findByUserId(userId);
        stickers = (albumId != null) ? 
            stickerRepository.findByStickersAlbumId(stickers, albumId) : 
            stickers;
        
        return stickers.stream().map(StickerDTO::new).toList();
    }

    public List<AlbumDTO> obtenerAlbumsDeUser(Long userId) {
        List<Sticker> stickers = userStickerRepository.findByUserId(userId);
        return stickerRepository.findDistinctAlbumsByStickers(stickers).stream().map(AlbumDTO::new).toList();
    }
}