package com.tpo.shopandpack;

import com.tpo.shopandpack.Strategy.IArmadoPackStrategy;
import com.tpo.shopandpack.Strategy.*;
import com.tpo.shopandpack.emun.Estrategia;

public class FactoryPack {

    public static IArmadoPackStrategy getEstrategia(Estrategia estrategia) {
        IArmadoPackStrategy instancia = null;

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