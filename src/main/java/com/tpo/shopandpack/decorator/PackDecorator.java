package com.tpo.shopandpack.decorator;

public class PackDecorator implements IPackDecorator {
    private IPackDecorator pack;

    public PackDecorator(IPackDecorator pack) {
        this.pack = pack;
    }

    public Double getPrecio() {
        return pack.getPrecio();
    }

    @Override
    public Long getId() {
        return pack.getId();
    }
}
