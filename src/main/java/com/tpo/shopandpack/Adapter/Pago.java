package com.tpo.shopandpack.Adapter;

import java.util.List;

import com.tpo.shopandpack.model.Sticker;

public class Pago {
    public void procesarPago(List<Sticker> stickers) {
        for (Sticker sticker : stickers) {
            sticker.reducirStock(1);
        }
    }
}
