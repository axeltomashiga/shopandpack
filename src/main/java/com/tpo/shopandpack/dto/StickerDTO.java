package com.tpo.shopandpack.dto;

import com.tpo.shopandpack.emun.Rareza;
import com.tpo.shopandpack.model.Sticker;


import lombok.Data;

@Data
public class StickerDTO {
    private String nombre;

    private String imagenUrl;

    private Integer numero;

    private Rareza rareza;

    public StickerDTO(Sticker sticker) {
        this.numero = sticker.getNumero();
        this.nombre = sticker.getNombre();
        this.rareza = sticker.getRareza();
        this.imagenUrl = sticker.getImagenUrl();
    }
}
