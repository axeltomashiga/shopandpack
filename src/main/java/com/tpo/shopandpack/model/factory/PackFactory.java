package com.tpo.shopandpack.model.factory;

import com.tpo.shopandpack.infrastructure.IPack;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.PackEspecial;
import com.tpo.shopandpack.model.TipoPack;
import com.tpo.shopandpack.model.User;

public class PackFactory {
    public static IPack crearPack(TipoPack tipo, User user, Album album) {
        return switch (tipo) {
            case BASICO -> new Pack(user, album);
            case ESPECIAL -> new PackEspecial();
        };
    }
}
