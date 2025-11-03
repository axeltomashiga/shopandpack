package com.tpo.shopandpack.service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.repository.StickerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StickerStockServiceTest {

    @Mock
    private StickerRepository stickerRepository;

    @InjectMocks
    private StickerStockService stickerStockService;

    @Test
    void reducirStockStickers_uses_bulk_when_all_counts_one() {
        Pack pack = new Pack();
        Sticker s1 = new Sticker();
        Sticker s2 = new Sticker();
        s1.setId(1L);
        s2.setId(2L);
        pack.setStickers(List.of(s1, s2));

        when(stickerRepository.reduceStockBulk(List.of(1L, 2L))).thenReturn(2);

        assertDoesNotThrow(() -> stickerStockService.reducirStockStickers(pack));

        verify(stickerRepository).reduceStockBulk(List.of(1L, 2L));
        verifyNoMoreInteractions(stickerRepository);
    }

    @Test
    void reducirStockStickers_uses_quantity_when_duplicates_present() {
        Pack pack = new Pack();
        Sticker s1 = new Sticker();
        Sticker s2 = new Sticker();
        Sticker s3 = new Sticker();
        s1.setId(1L);
        s2.setId(1L);
        s3.setId(2L);
        pack.setStickers(List.of(s1, s2, s3));

        when(stickerRepository.reduceStockByQuantity(1L, 2)).thenReturn(1);
        when(stickerRepository.reduceStockByQuantity(2L, 1)).thenReturn(1);

        assertDoesNotThrow(() -> stickerStockService.reducirStockStickers(pack));

        verify(stickerRepository).reduceStockByQuantity(1L, 2);
        verify(stickerRepository).reduceStockByQuantity(2L, 1);
        verifyNoMoreInteractions(stickerRepository);
    }
}
