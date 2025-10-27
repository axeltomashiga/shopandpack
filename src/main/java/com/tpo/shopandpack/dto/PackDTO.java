package com.tpo.shopandpack.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.tpo.shopandpack.model.Pack;

import lombok.Data;

@Data
public class PackDTO {
    private Long id;

    private LocalDateTime createdAt;

    private List<StickerDTO> stickers;

    public PackDTO(Pack pack) {
        this.id = pack.getId();
    
        this.createdAt = pack.getCreatedAt();

        this.stickers = pack.getStickers()
                            .stream()
                            .map(StickerDTO::new)
                            .collect(Collectors.toList());
    }
}
