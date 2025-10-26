package com.tpo.shopandpack.service;

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

    @Autowired
    private UserStickerRepository userStickerRepository;

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

        for (Sticker sticker : stickers) {
            int updatedRows = stickerRepository.reduceStock(sticker.getId());
            if (updatedRows == 0) {
                throw new NotStickersAvailable("Figurita no disponible: " + sticker.getNombre());
            }
            UserSticker userSticker = new UserSticker(user, sticker);
            userStickerRepository.save(userSticker);
        }

        Pack pack = new Pack(user, album);
        packageRepository.save(pack);

        return new Pack();
    }

    public Double obtenerPrecio() {
        return 9.99;
    }
}
