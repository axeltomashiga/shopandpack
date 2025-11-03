package com.tpo.shopandpack.service;

import org.springframework.stereotype.Component;

import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.User;
import java.util.List;

@Component
public class PackFactory {

    /**
     * Crea un Pack inicializado pero no persistido.
     */
    public Pack createPack(User user, Album album) {
        Pack pack = new Pack(user, album);
        // Inicializaciones adicionales si se necesitan pueden ir aquí
        return pack;
    }

    /**
     * Crea un Pack inicializado con las figuritas (no persistido).
     */
    public Pack createPack(User user, Album album, List<Sticker> stickers) {
        Pack pack = new Pack(user, album);
        // centralizar la asignación de stickers en la factory para evitar setters dispersos
        pack.setStickers(stickers);
        return pack;
    }
}
