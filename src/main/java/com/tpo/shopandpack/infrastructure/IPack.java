package com.tpo.shopandpack.infrastructure;
import java.util.List;

import com.tpo.shopandpack.model.Sticker;

public interface IPack {
    void agregarSticker(Sticker sticker);
    boolean estaCompleto();
    List<Sticker> getStickers();
    boolean equals(Object o);
    int hashCode();
    String getTipo(); 
}
