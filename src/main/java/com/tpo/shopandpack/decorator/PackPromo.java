package com.tpo.shopandpack.decorator;

import com.tpo.shopandpack.model.IPack;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import lombok.Getter;

import java.util.List;

/**
 * Patrón Decorator para Pack
 * Permite agregar descuentos a un pack existente sin modificar la clase Pack
 */
@Getter
public class PackPromo implements IPack {
    
    private final Pack pack;
    private final Double descuento; // Descuento como decimal (ej: 0.20 = 20%)
    
    /**
     * Constructor del decorador PackPromo
     * @param pack El pack a decorar
     * @param descuento El porcentaje de descuento como decimal (0.0 a 1.0)
     */
    public PackPromo(Pack pack, Double descuento) {
        if (pack == null) {
            throw new IllegalArgumentException("El pack no puede ser null");
        }
        if (descuento < 0 || descuento > 1.0) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 1");
        }
        this.pack = pack;
        this.descuento = descuento;
    }
    
    /**
     * Constructor alternativo con descuento como porcentaje entero
     * @param pack El pack a decorar
     * @param descuentoPorcentaje El porcentaje de descuento (ej: 20 para 20%)
     */
    public PackPromo(Pack pack, int descuentoPorcentaje) {
        this(pack, descuentoPorcentaje / 100.0);
    }
    
    /**
     * Calcula el precio con descuento aplicado
     * @return El precio del pack con el descuento aplicado
     */
    @Override
    public Double getPrecio() {
        Double precioOriginal = pack.getPrecio();
        Double montoDescuento = precioOriginal * descuento;
        Double precioFinal = precioOriginal - montoDescuento;
        return Math.round(precioFinal * 100.0) / 100.0; // Redondear a 2 decimales
    }
    
    /**
     * Obtiene los stickers del pack decorado
     * @return Lista de stickers del pack original
     */
    @Override
    public List<Sticker> getStickers() {
        return pack.getStickers();
    }
    
    /**
     * Crea o inicializa el pack
     * En este caso, delega la creación al pack original
     */
    @Override
    public void crear() {
        // El pack ya existe, no es necesario crear nada
        // Este método está aquí por el contrato de la interfaz IPack
    }
    
    /**
     * Obtiene el monto del descuento aplicado
     * @return El monto en pesos del descuento
     */
    public Double getMontoDescuento() {
        Double precioOriginal = pack.getPrecio();
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
    
    /**
     * Obtiene el pack original sin decorar
     * @return El pack base
     */
    public Pack getPackOriginal() {
        return pack;
    }
    
    @Override
    public String toString() {
        return String.format("PackPromo{pack=%s, descuento=%.0f%%, precioOriginal=%.2f, precioFinal=%.2f}",
                pack.getId(),
                getDescuentoPorcentaje(),
                pack.getPrecio(),
                getPrecio());
    }
}

