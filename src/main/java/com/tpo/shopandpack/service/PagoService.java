package com.tpo.shopandpack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.model.Pago;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.model.UserSticker;
import com.tpo.shopandpack.repository.PagoRepository;
import com.tpo.shopandpack.repository.StickerRepository;
import com.tpo.shopandpack.repository.UserStickerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private UserStickerRepository userStickerRepository;

    @Autowired
    private StickerRepository stickerRepository;
    
    public void procesar(Pack pack) {
        User user = pack.getUser();
        Pago pago = new Pago(pack, user);

        List<Sticker> stickers = pack.getStickers();

        userStickerRepository.saveAll(
            stickers.stream()
                .map(sticker -> new UserSticker(user, sticker))
                .toList()
        );

        for (Sticker sticker : stickers) {
            stickerRepository.reducirStock(sticker.getId());
        }

        pagoRepository.save(pago);
    }
}