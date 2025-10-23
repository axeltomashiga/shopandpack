package com.tpo.shopandpack.dto;

import lombok.Data;

@Data
public class CompraDTO {
    private Long userId;
    private Integer cantidadPaquetes;
    private Double montoTotal;
    private String metodoPago;
    private String tipoPack;
}
