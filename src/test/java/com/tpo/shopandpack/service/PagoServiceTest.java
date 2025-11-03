package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private com.tpo.shopandpack.repository.PagoRepository pagoRepository;

    @Mock
    private StickerStockService stickerStockService;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void procesarPago_delegates_to_stickerStockService() {
        Pack pack = new Pack();

        pagoService.procesarPago(pack);

        verify(stickerStockService).reducirStockStickers(pack);
    }
}
