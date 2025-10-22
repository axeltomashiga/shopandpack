package com.tpo.shopandpack.dto;

import com.tpo.shopandpack.emun.Rareza;

import lombok.Data;

@Data
public class StickerDTO {
    private Long id;

    private String nombre;

    private String imagenUrl;

    private Integer numero;

    private Rareza rareza;

    private Long albumId;

}
