package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;

import java.util.List;

import com.tpo.shopandpack.model.Sticker;

public class PagoService {
    public void procesar(Pack pack) {
        List<Sticker> stickers = pack.getStickers();
        for (Sticker sticker : stickers) {
            sticker.reducirStock(1);
        }
    }
}