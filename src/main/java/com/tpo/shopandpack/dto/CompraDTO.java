package com.tpo.shopandpack.dto;

import lombok.Data;

@Data
public class CompraDTO {
    private Long userId;
    private Long albumId;
    private Integer cantidadPaquetes;
    private Double montoTotal;
    private String metodoPago;
}
