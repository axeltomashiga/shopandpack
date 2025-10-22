package com.tpo.shopandpack.emun;

public enum Rareza {
    COMUN(70),    // 70% probabilidad
    RARA(25),     // 25% probabilidad  
    EPICA(5);     // 5% probabilidad
    
    private final int probabilidad;
    
    Rareza(int probabilidad) {
        this.probabilidad = probabilidad;
    }
    
    public int getProbabilidad() {
        return probabilidad;
    }
}
