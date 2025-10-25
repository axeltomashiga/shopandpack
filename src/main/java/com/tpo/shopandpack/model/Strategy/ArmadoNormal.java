package com.tpo.shopandpack.model.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpo.shopandpack.emun.Rareza;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.repository.StickerRepository;

public class ArmadoNormal implements IArmadoStrategy {
    @Autowired
    private StickerRepository stickerRepo;

    @Override
    public List<Sticker> armarPack() {
        // Ejemplo: 3 comunes, 1 raro, 1 uncommon (ajusta según reglas)
        List<Sticker> result = new ArrayList<>();
        result.addAll(pickRandom(Rareza.COMUN, 3));
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            Double randomDouble = random.nextDouble();
            if(randomDouble < 0.3) {
                result.addAll(pickRandom(Rareza.EPICA, 1));
            } else if(randomDouble < 0.6) {
                result.addAll(pickRandom(Rareza.RARA, 1));
            } else {
                result.addAll(pickRandom(Rareza.COMUN, 1));
            }
        }
        return result;
    }

    private List<Sticker> pickRandom(Rareza r, int n) {
        List<Sticker> pool = stickerRepo.findByRareza(r);
        Collections.shuffle(pool);
        return pool.stream().limit(n).collect(Collectors.toList());
    }
}
