package com.tpo.shopandpack.model;

import java.util.List;

/**
 * Interfaz base para el patrón Decorator de Pack
 * Define el contrato común entre Pack y sus decoradores (como PackPromo)
 */
public interface IPack {
    
    /**
     * Obtiene el precio del pack (puede estar decorado con descuentos u otras modificaciones)
     * @return El precio del pack
     */
    Double getPrecio();
    
    /**
     * Obtiene la lista de stickers del pack
     * @return Lista de stickers
     */
    List<Sticker> getStickers();
    
    /**
     * Crea o inicializa el pack
     * Este método permite la construcción del pack según el patrón Factory
     */
    void crear();
}

