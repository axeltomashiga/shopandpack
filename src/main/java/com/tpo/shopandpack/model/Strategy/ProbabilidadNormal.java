package com.tpo.shopandpack.model.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.service.StickerService;

@Component
public class ProbabilidadNormal implements IProbabilidadStickerStrategy {
    @Autowired
    private StickerService stickerService;
    
    @Override
    public List<Sticker> listarStickers() {
        List<Sticker> stickers = new ArrayList<>();
        List<Sticker> todosStickers = stickerService.encontrarStickers();
        randomlyAdd(stickers, todosStickers, 5);
        return stickers;
    }
    
    @Override
    public List<Sticker> listarStickers(com.tpo.shopandpack.model.Album album) {
        List<Sticker> stickers = new ArrayList<>();
        List<Sticker> stickersDelAlbum = stickerService.findByAlbumIdAndStockDisponibleGreaterThan(album.getId(), 0);
        randomlyAdd(stickers, stickersDelAlbum, 5);
        return stickers;
    }

    private void randomlyAdd(List<Sticker> target, List<Sticker> source, int count) {
        List<Sticker> shuffled = new ArrayList<>(source);
        Collections.shuffle(shuffled);
        int limit = Math.min(count, shuffled.size());
        target.addAll(shuffled.subList(0, limit));
    }
    
}
