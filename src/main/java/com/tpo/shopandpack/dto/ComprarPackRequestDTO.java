package com.tpo.shopandpack.dto;

import lombok.Data;

@Data
public class ComprarPackRequestDTO {
    Long userId;
    String tipoPago;

    public ComprarPackRequestDTO() {}

    public ComprarPackRequestDTO(Long userId, String tipoPago) {
        this.userId = userId;
        this.tipoPago = tipoPago;
    }
}
