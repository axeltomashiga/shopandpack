package com.tpo.shopandpack;

import com.tpo.shopandpack.Strategy.IStickerSelectionStrategy;
import com.tpo.shopandpack.Strategy.*;
import com.tpo.shopandpack.emun.Estrategia;

public class FactoryPack {

    public static IStickerSelectionStrategy getEstrategia(Estrategia estrategia) {
        IStickerSelectionStrategy instancia = null;

        switch (estrategia) {
            case UNIFORM:
                instancia = new UniformDistributionStrategy(); break;
            case WEIGHTED:
                instancia = new WeightedDistributionStrategy();  break;
            default:
                throw new IllegalArgumentException("Estrategia no soportada: " + estrategia);
        }

        return instancia;
    }
}