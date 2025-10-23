package com.tpo.shopandpack.model.Strategy;

import java.util.List;

import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Sticker;

public interface IProbabilidadStickerStrategy {
    List<Sticker> listarStickers();
    List<Sticker> listarStickers(Album album);
}
