package com.tpo.shopandpack.dto;

import com.tpo.shopandpack.model.Album;

import lombok.Data;

@Data
public class AlbumDTO {
    private String titulo;

    public AlbumDTO(Album album) {
        this.titulo = album.getTitulo();
    }
}
