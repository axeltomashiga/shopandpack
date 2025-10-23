package com.tpo.shopandpack.model.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.service.StickerService;

@Component
public class RaroOMasAsegurada implements IProbabilidadStickerStrategy {
    @Autowired
    private StickerService stickerService;
    @Override
    public List<Sticker> listarStickers() {
        List<Sticker> stickers = new ArrayList<>();
        List<Sticker> stickersRaros = stickerService.encontrarStickersPorStock(1, 50);
        List<Sticker> stickersComunes = stickerService.encontrarStickersPorStock(51, 1000);
        randomlyAdd(stickers, stickersRaros, 1);
        randomlyAdd(stickers, stickersComunes, 4);
        return stickers;
    }
    
    @Override
    public List<Sticker> listarStickers(com.tpo.shopandpack.model.Album album) {
        List<Sticker> stickers = new ArrayList<>();
        List<Sticker> stickersDelAlbum = stickerService.findByAlbumIdAndStockDisponibleGreaterThan(album.getId(), 0);
        
        // Separar por rareza usando el campo rareza de la entidad
        List<Sticker> stickersRaros = stickersDelAlbum.stream()
            .filter(s -> "RARA".equals(s.getRareza()) || "EPICA".equals(s.getRareza()))
            .collect(java.util.stream.Collectors.toList());
        List<Sticker> stickersComunes = stickersDelAlbum.stream()
            .filter(s -> "COMUN".equals(s.getRareza()))
            .collect(java.util.stream.Collectors.toList());
        
        // Garantizar al menos 1 raro/épico si existe
        if (!stickersRaros.isEmpty()) {
            randomlyAdd(stickers, stickersRaros, 1);
            randomlyAdd(stickers, stickersDelAlbum, 4); // Completar con cualquiera
        } else {
            randomlyAdd(stickers, stickersComunes, 5);
        }
        return stickers;
    }
    
    private void randomlyAdd(List<Sticker> target, List<Sticker> source, int count) {
        List<Sticker> shuffled = new ArrayList<>(source);
        Collections.shuffle(shuffled);
        int limit = Math.min(count, shuffled.size());
        target.addAll(shuffled.subList(0, limit));
    }

}
