package com.tpo.shopandpack.emun;

public enum Rareza {
    COMUN(200),
    RARA(100),  
    EPICA(50);
    
    private final int stock_inicial;
    
    Rareza(int stock_inicial) {
        this.stock_inicial = stock_inicial;
    }
    
    public int getStock() {
        return stock_inicial;
    }
}
