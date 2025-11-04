package com.tpo.shopandpack.decorator;

import com.tpo.shopandpack.model.Pack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PackDecoratorTest {

    @Test
    void getPrecio_and_descuento_calculations() {
        Pack pack = new Pack();
        pack.setPrecio(100.00);

        IPackDecorator promo = new PackPromo(pack, 20); // 20%

        assertEquals(80.00, promo.getPrecio());
    }
    
    @Test
    void getPrecio_and_multiplicador_calculations() {
        Pack pack = new Pack();
        pack.setPrecio(100.00);

        IPackDecorator promo = new PackEspecial(pack, 20); // 20%

        assertEquals(120.00, promo.getPrecio());
    }

    @Test
    void getPrecio_and_multiplicador_and_discount_calculations() {
        Pack pack = new Pack();
        pack.setPrecio(100.00);

        IPackDecorator promo = new PackPromo(new PackEspecial(pack, 20), 20); // 20%

        assertEquals(96.00, promo.getPrecio());
    }
}
