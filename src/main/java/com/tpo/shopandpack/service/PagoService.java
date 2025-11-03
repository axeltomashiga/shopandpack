package com.tpo.shopandpack.service;

import org.springframework.stereotype.Service;

import com.tpo.shopandpack.dto.ComprarPackRequestDTO;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Pago;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.repository.PagoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    private final PagoRepository pagoRepository;
    private final StickerStockService stickerStockService;

    public PagoService(PagoRepository pagoRepository, StickerStockService stickerStockService) {
        this.pagoRepository = pagoRepository;
        this.stickerStockService = stickerStockService;
    }
    
    public void procesarPago(Pack pack) {
        // Usar el servicio que aplica el decremento atómico en BD
        stickerStockService.reducirStockStickers(pack);
    }

    public void procesarPago(Pack pack, ComprarPackRequestDTO request) {
        User user = pack.getUser();
        Pago pago = new Pago(pack, user);

        // Reducir stock de stickers de forma atómica en BD
        stickerStockService.reducirStockStickers(pack);

        pagoRepository.save(pago);
    }
}