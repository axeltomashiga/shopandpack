package com.tpo.shopandpack.Strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.tpo.shopandpack.emun.Rareza;
import com.tpo.shopandpack.model.Sticker;
import org.springframework.stereotype.Component;

@Component
public class UniformDistributionStrategy implements IArmadoPackStrategy {

    public List<Sticker> armarPack(List<Sticker> stickersDisponibles) {
        // Ejemplo: 3 comunes, 1 raro, 1 uncommon (ajusta según reglas)
        List<Sticker> result = new ArrayList<>();
        result.addAll(pickRandom(Rareza.COMUN, 3, stickersDisponibles));
        Random random = new Random();
        for (int i = 0; i <= 1; i++) {
            Double randomDouble = random.nextDouble();
            if(randomDouble < 0.3) {
                result.addAll(pickRandom(Rareza.EPICA, 1, stickersDisponibles));
            } else if(randomDouble < 0.6) {
                result.addAll(pickRandom(Rareza.RARA, 1, stickersDisponibles));
            } else {
                result.addAll(pickRandom(Rareza.COMUN, 1, stickersDisponibles));
            }
        }
        return result;
    }

    private List<Sticker> pickRandom(Rareza r, int n, List<Sticker> stickersDisponibles) {
        List<Sticker> pool = stickersDisponibles.stream()
                .filter(s -> s.getRareza() == r)
                .collect(Collectors.toList());
        Collections.shuffle(pool);
        return pool.stream().limit(n).collect(Collectors.toList());
    }
}
