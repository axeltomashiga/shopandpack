package com.tpo.shopandpack.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.UserSticker;
import com.tpo.shopandpack.repository.PackageRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.repository.UserStickerRepository;

@Service
public class PackService {

    @Autowired
    private StickerRepository stickerRepository;

    @Autowired
    private UserStickerRepository userStickerRepository;

    @Autowired
    private PackageRepository packageRepository;

    /**
     * Persiste las figuritas asociadas al pack y crea la relación usuario↔figurita (UserSticker).
     * Se asume que el pack ya fue guardado con ID (persisted) antes de llamar a este método.
     */
    public void persistStickersAndRelations(Pack pack) {
        List<Sticker> stickers = pack.getStickers();

        if (stickers == null || stickers.isEmpty()) {
            return; // nada que persistir
        }

        // Guardar/actualizar stickers (pueden ser nuevos o modificados)
        List<Sticker> saved = stickerRepository.saveAll(stickers);

        // Re-guardar el pack por si la relación ManyToMany necesita stickers con ID
        packageRepository.save(pack);

        // Crear y guardar relaciones UserSticker
        List<UserSticker> userStickers = saved.stream()
                .map(s -> new UserSticker(pack.getUser(), s))
                .collect(Collectors.toList());

        userStickerRepository.saveAll(userStickers);
    }

    /**
     * Guarda/actualiza sólo los stickers y el pack (sin crear las relaciones UserSticker).
     * Útil para obtener IDs de stickers antes de procesar el pago.
     */
    public void saveStickersAndPack(Pack pack) {
        List<Sticker> stickers = pack.getStickers();
        if (stickers == null || stickers.isEmpty()) return;
        List<Sticker> saved = stickerRepository.saveAll(stickers);
        // actualizar la lista con los stickers persistidos (con IDs)
        pack.setStickers(saved);
        packageRepository.save(pack);
    }

    /**
     * Persiste únicamente las relaciones UserSticker (asume que stickers y pack ya fueron persistidos y tienen IDs).
     */
    public void persistUserStickerRelations(Pack pack) {
        List<Sticker> stickers = pack.getStickers();
        if (stickers == null || stickers.isEmpty()) return;
        List<UserSticker> userStickers = stickers.stream()
                .map(s -> new UserSticker(pack.getUser(), s))
                .collect(Collectors.toList());
        userStickerRepository.saveAll(userStickers);
    }

    public boolean estaCompleto(Pack pack) {
        return pack.getStickers().size() == 5;
    }

    /**
     * Agrega una figurita al pack y persiste los cambios.
     * Valida la regla del máximo de 5 figuritas.
     */
    public void agregarSticker(Pack pack, Sticker sticker) {
        if (pack == null || sticker == null) {
            throw new IllegalArgumentException("Pack y sticker no pueden ser nulos");
        }

        List<Sticker> current = pack.getStickers();
        if (current.size() >= 5) {
            throw new IllegalStateException("El paquete ya tiene 5 figuritas");
        }

        List<Sticker> next = new java.util.ArrayList<>(current);
        next.add(sticker);

        // usar el setter generado por Lombok para actualizar la lista interna
        pack.setStickers(next);

        // persistir cambios: stickers + pack + relaciones
        persistStickersAndRelations(pack);
    }

    /**
     * Agrega varias figuritas al pack respetando el límite de 5 y persiste los cambios.
     */
    public void agregarStickers(Pack pack, List<Sticker> stickersToAdd) {
        if (pack == null || stickersToAdd == null || stickersToAdd.isEmpty()) {
            return;
        }

        List<Sticker> current = pack.getStickers();
        if (current.size() + stickersToAdd.size() > 5) {
            throw new IllegalStateException("Agregar estas figuritas excede el límite de 5");
        }

        List<Sticker> next = new java.util.ArrayList<>(current);
        next.addAll(stickersToAdd);
        pack.setStickers(next);

        persistStickersAndRelations(pack);
    }
}
