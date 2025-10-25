package com.tpo.shopandpack.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.tpo.shopandpack.emun.Rareza;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.repository.StickerRepository;

import org.springframework.stereotype.Component;

@Component
public class UniformDistributionStrategy implements IArmadoPackStrategy {
    @Autowired
    private StickerRepository stickerRepo;

    public List<Sticker> armarPack(Album album) {
        // Ejemplo: 3 comunes, 1 raro, 1 uncommon (ajusta según reglas)
        List<Sticker> result = new ArrayList<>();
        result.addAll(pickRandom(Rareza.COMUN, 3, album));
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            Double randomDouble = random.nextDouble();
            if(randomDouble < 0.3) {
                result.addAll(pickRandom(Rareza.EPICA, 1, album));
            } else if(randomDouble < 0.6) {
                result.addAll(pickRandom(Rareza.RARA, 1, album));
            } else {
                result.addAll(pickRandom(Rareza.COMUN, 1, album));
            }
        }
        return result;
    }

    private List<Sticker> pickRandom(Rareza r, int n, Album album) {
        List<Sticker> pool = stickerRepo.findByRarezaAndAlbum(r, album);
        Collections.shuffle(pool);
        return pool.stream().limit(n).collect(Collectors.toList());
    }
}
