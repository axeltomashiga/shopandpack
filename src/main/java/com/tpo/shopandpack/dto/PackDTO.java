package com.tpo.shopandpack.dto;

import java.util.ArrayList;
import java.util.List;


import lombok.Data;

@Data
public class PackDTO {
    private Long id;

    private Long usuarioId;

    private Long albumId;

    private List<StickerDTO> items = new ArrayList<>();
}
