package com.tpo.shopandpack.decorator;

/**
 * Patrón Decorator para Pack
 * Permite agregar descuentos a un pack existente sin modificar la clase Pack
 */
public class PackPromo extends PackDecorator {
    
    private final Double descuento; // Descuento como decimal (ej: 0.20 = 20%)
    
    public PackPromo(IPackDecorator pack, Double descuento) {
        super(pack);
        this.descuento = descuento;
    }
    
    public PackPromo(IPackDecorator pack, int descuento) {
        super(pack);
        this.descuento = (double) descuento / 100;
    }
    /**
     * Calcula el precio con descuento aplicado
     * @return El precio del pack con el descuento aplicado
     */
    public Double getPrecio() {
        Double precioOriginal = super.getPrecio();
        Double montoDescuento = precioOriginal * descuento;
        Double precioFinal = precioOriginal - montoDescuento;
        return Math.round(precioFinal * 100.0) / 100.0; // Redondear a 2 decimales
    }
    

    /**
     * Obtiene el monto del descuento aplicado
     * @return El monto en pesos del descuento
     */
    public Double getMontoDescuento() {
        Double precioOriginal = super.getPrecio();
        Double monto = precioOriginal * descuento;
        return Math.round(monto * 100.0) / 100.0; // Redondear a 2 decimales
    }
    
    /**
     * Obtiene el porcentaje de descuento
     * @return El descuento como porcentaje (0-100)
     */
    public Double getDescuentoPorcentaje() {
        return (double) Math.round(descuento * 100.0);
    }
    
    @Override
    public String toString() {
        return String.format("PackPromo{pack=%s, descuento=%.0f%%, precioOriginal=%.2f, precioFinal=%.2f}",
                super.getId(),
                getDescuentoPorcentaje(),
                super.getPrecio(),
                getPrecio());
    }
}

