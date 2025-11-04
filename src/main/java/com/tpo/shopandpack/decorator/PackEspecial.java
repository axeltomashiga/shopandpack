package com.tpo.shopandpack.decorator;

public class PackEspecial extends PackDecorator {
    private final Double multiplicador; // Multiplicador como fracción (ej: 0.20 = 20%)
    
    
    public PackEspecial(IPackDecorator pack, Double multiplicador) {
        super(pack);
        this.multiplicador = multiplicador;
    }
    public PackEspecial(IPackDecorator pack, int multiplicador) {
        super(pack);
        // Convertir porcentaje entero a fracción: 20 -> 0.20
        this.multiplicador = multiplicador / 100.0;
    }
    
    /**
     * Calcula el precio con descuento aplicado
     * @return El precio del pack con el descuento aplicado
     */
    public Double getPrecio() {
        Double precioOriginal = super.getPrecio();
        // Aumentar el precio según el multiplicador (ej: 20% -> precio * 1.20)
        Double precioFinal = precioOriginal * (1 + multiplicador);
        return Math.round(precioFinal * 100.0) / 100.0; // Redondear a 2 decimales
    }
    
    public Double getMontoMultiplicador() {
        Double precioOriginal = super.getPrecio();
        Double monto = precioOriginal * multiplicador;
        return Math.round(monto * 100.0) / 100.0; // Redondear a 2 decimales
    }
    
    /**
     * Obtiene el porcentaje de multiplicador
     * @return El multiplicador como porcentaje (0-100)
     */
    public Double getMultiplicadorPorcentaje() {
        return (double) Math.round(multiplicador * 100.0);
    }
    
    @Override
    public String toString() {
        return String.format("PackEspecial{pack=%s, multiplicador=%.0f%%, precioOriginal=%.2f, precioFinal=%.2f}",
                super.getId(),
                getMultiplicadorPorcentaje(),
                super.getPrecio(),
                getPrecio());
    }
}
