package com.tpo.shopandpack.model.Factory;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Strategy.ProbabilidadNormal;
import com.tpo.shopandpack.model.Strategy.RaroOMasAsegurada;

public class PackFactory {
    public static Pack crearPack(String tipo) {
        switch (tipo) {
            case "normal":
                Pack normalPack = new Pack();
                normalPack.setEstrategiaProbabilidad(new ProbabilidadNormal());
                return normalPack;
            case "premium":
                Pack premiumPack = new Pack();
                premiumPack.setEstrategiaProbabilidad(new RaroOMasAsegurada());
                return premiumPack;
            default:
                throw new IllegalArgumentException("Tipo de pack desconocido");
        }
    }
}
