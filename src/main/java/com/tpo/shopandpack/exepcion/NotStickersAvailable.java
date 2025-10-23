package com.tpo.shopandpack.exepcion;

public class NotStickersAvailable extends RuntimeException {
    
    public NotStickersAvailable(String message) {
        super(message);
    }
}
