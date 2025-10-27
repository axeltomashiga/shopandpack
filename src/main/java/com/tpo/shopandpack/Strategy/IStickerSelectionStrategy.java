package com.tpo.shopandpack.Strategy;

import java.util.List;

import com.tpo.shopandpack.model.Sticker;

public interface IStickerSelectionStrategy {
    List<Sticker> selectStickers(List<Sticker> stickersDisponibles);
}