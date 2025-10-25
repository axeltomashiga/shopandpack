package com.tpo.shopandpack;

import com.tpo.shopandpack.Strategy.IArmadoPackStrategy;
import com.tpo.shopandpack.Strategy.*;
import com.tpo.shopandpack.emun.Estrategia;

public class FactoryPack {

    public static IArmadoPackStrategy getEstrategia(Estrategia estrategia) {


        switch (estrategia) {
            case UNIFORM:
                return new UniformDistributionStrategy(); 
            case WEIGHTED:
                return new WeightedDistributionStrategy(); 
            default:
                throw new IllegalArgumentException("Estrategia no soportada: " + estrategia);
        }


    }
}