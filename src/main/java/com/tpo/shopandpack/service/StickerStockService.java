package com.tpo.shopandpack.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpo.shopandpack.exepcion.NotStickersAvailable;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.repository.StickerRepository;

@Service
public class StickerStockService {

    private final StickerRepository stickerRepository;

    public StickerStockService(StickerRepository stickerRepository) {
        this.stickerRepository = stickerRepository;
    }

    /**
     * Reduce atomically el stock de cada sticker del pack usando un UPDATE atómico en la BD.
     * Lanza NotStickersAvailable si alguno no pudo decrementar su stock.
     */
    @Transactional
    public void reducirStockStickers(Pack pack) {
        // Agrupar por id para minimizar número de queries (si hay duplicados aplicar cantidad)
        var stickers = pack.getStickers();
        if (stickers.isEmpty()) return;

        java.util.Map<Long, Integer> counts = new java.util.HashMap<>();
        for (Sticker s : stickers) {
            Long id = s.getId();
            if (id == null) {
                throw new NotStickersAvailable("Sticker sin id en pack: no se puede decrementar stock");
            }
            counts.merge(id, 1, Integer::sum);
        }

        // Si todos los conteos son 1 podemos usar la operación bulk (mejor rendimiento)
        boolean allOnes = counts.values().stream().allMatch(c -> c == 1);
        if (allOnes) {
            java.util.List<Long> ids = new java.util.ArrayList<>(counts.keySet());
            int updated = stickerRepository.reduceStockBulk(ids);
            if (updated != ids.size()) {
                // Fallback: intentar por cada id para identificar el problema y lanzar excepción
                for (Long id : ids) {
                    int up = stickerRepository.reduceStock(id);
                    if (up != 1) throw new NotStickersAvailable("Stock insuficiente para sticker " + id);
                }
            }
            return;
        }

        // Para ids con cantidad >1 usamos reduceStockByQuantity por cada id único
        for (var entry : counts.entrySet()) {
            Long id = entry.getKey();
            int qty = entry.getValue();
            int updated = stickerRepository.reduceStockByQuantity(id, qty);
            if (updated != 1) {
                throw new NotStickersAvailable("Stock insuficiente para sticker " + id);
            }
        }
    }
}
