package com.tpo.shopandpack.decorator;

import com.tpo.shopandpack.model.IPack;
import com.tpo.shopandpack.model.Pack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackPromoTest {

    @Test
    void getPrecio_and_descuento_calculations() {
        Pack pack = new Pack();
        pack.setPrecio(100.00);

        PackPromo promo = new PackPromo((IPack) pack, 20); // 20%

        assertEquals(80.00, promo.getPrecio());
        assertEquals(20.00, promo.getMontoDescuento());
        assertEquals(20.0, promo.getDescuentoPorcentaje());
    }
    
}
