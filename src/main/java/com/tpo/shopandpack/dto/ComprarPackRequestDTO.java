package com.tpo.shopandpack.dto;

import lombok.Data;

@Data
public class ComprarPackRequestDTO {
    Long userId;
    Long descuento;

    public ComprarPackRequestDTO() {}

    public ComprarPackRequestDTO(Long userId, Long descuento) {
        this.userId = userId;
        this.descuento = descuento;
    }
}
