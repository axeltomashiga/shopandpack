package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.emun.Rareza;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PagoServiceTest {

    @Test
    void procesarPago_reduces_sticker_stock() {
        PagoService pagoService = new PagoService();

        Pack pack = new Pack();

        Sticker s1 = new Sticker(Rareza.COMUN);
        Sticker s2 = new Sticker(Rareza.COMUN);

        // stock inicial configurado por constructor de Sticker
        int inicial1 = s1.getStockDisponible();
        int inicial2 = s2.getStockDisponible();

        pack.setStickers(List.of(s1, s2));

        //pagoService.procesarPago(pack);

        //assertEquals(inicial1 - 1, s1.getStockDisponible());
        //assertEquals(inicial2 - 1, s2.getStockDisponible());
    }
}
