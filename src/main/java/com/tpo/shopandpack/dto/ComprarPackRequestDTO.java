package com.tpo.shopandpack.dto;

import lombok.Data;

@Data
public class ComprarPackRequestDTO {
    Long userId;

    public ComprarPackRequestDTO() {}

    public ComprarPackRequestDTO(Long userId) {
        this.userId = userId;
    }
}
