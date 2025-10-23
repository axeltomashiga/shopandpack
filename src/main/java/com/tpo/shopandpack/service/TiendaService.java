package com.tpo.shopandpack.service;

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


        Pack pack = new Pack();
        pack.setId(1L);
        return pack;
    }

    public Double obtenerPrecio() {
        return 9.99;
    }
}
