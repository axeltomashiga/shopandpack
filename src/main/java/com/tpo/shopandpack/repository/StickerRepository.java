package com.tpo.shopandpack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tpo.shopandpack.model.Sticker;

public interface StickerRepository extends JpaRepository<Sticker, Long>{
    @Query("SELECT s FROM Sticker s WHERE s.stockTotal between ?1 and ?2 and s.stockDisponible > 0")
    List<Sticker> encontrarStickersPorStock(int stockTotal,int stockTotal2);

    @Query("SELECT s FROM Sticker s WHERE s.stockTotal > 0 and s.stockDisponible > 0")
    List<Sticker> encontrarStickers();
    
    @Query("SELECT s FROM Sticker s WHERE s.album.id = ?1 AND s.stockDisponible > ?2")
    List<Sticker> findByAlbumIdAndStockDisponibleGreaterThan(Long albumId, Integer stock);
}