package com.tpo.shopandpack.adapter;

public class PagoDesc {
    public void autorizar() {
        System.out.println("Autorizando pago...");
    }
    public void cobrar() {
        System.out.println("Cobrando pago...");
    }
    public void cerrar() {
        System.out.println("Cerrando operación...");
    }
}