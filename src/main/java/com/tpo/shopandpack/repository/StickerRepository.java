package com.tpo.shopandpack.repository;

import com.tpo.shopandpack.model.Sticker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import jakarta.transaction.Transactional;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long>{

    /**
     * Encuentra figuritas disponibles de un álbum (con stock > 0)
     */
    @Query("SELECT s FROM Sticker s WHERE s.album.id = :albumId AND s.stockDisponible > 0")
    List<Sticker> findAvailableByAlbumId(@Param("albumId") Long albumId);


    /**
     * Disminuye el stock de un sticker en 1 si hay stock disponible.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Sticker s SET s.stockDisponible = s.stockDisponible - 1 WHERE s.id = :stickerId AND s.stockDisponible > 0")
    int reducirStock(@Param("stickerId") Long stickerId);

    @Query("SELECT s FROM Sticker s WHERE s IN :stickers AND s.album.id = :albumId")
    List<Sticker> findByStickersAlbumId(@Param("stickers") List<Sticker> stickers, @Param("albumId") Long albumId);


    List<Sticker> findByAlbumId(Long albumId);
}