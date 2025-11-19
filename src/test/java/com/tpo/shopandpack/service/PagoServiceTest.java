package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.repository.PackageRepository;
import com.tpo.shopandpack.repository.PagoRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.repository.UserStickerRepository;
import com.tpo.shopandpack.emun.Rareza;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private UserStickerRepository userStickerRepository;

    @Mock
    private StickerRepository stickerRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void procesarPago_reduces_sticker_stock() {
        Pack pack = new Pack();

        Sticker s1 = new Sticker(Rareza.COMUN);
        Sticker s2 = new Sticker(Rareza.COMUN);

    // We verify that the service attempts to reduce stock via repository calls.
    pack.setStickers(List.of(s1, s2));

    pagoService.procesar(pack, "efectivo");

    // Expect two calls (one per sticker) to reducirStock. Use any() because stickers have no IDs in this unit test.
    verify(stickerRepository, times(2)).reducirStock(any());
    // Also ensure a Pago was persisted
    verify(pagoRepository, times(1)).save(any());
    }
}
