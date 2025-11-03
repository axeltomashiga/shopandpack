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
    int reduceStock(@Param("stickerId") Long stickerId);

    /**
     * Disminuye el stock de un sticker en la cantidad indicada si hay stock disponible.
     * Devuelve el número de filas actualizadas (1 si se pudo decrementar, 0 si no había stock suficiente).
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Sticker s SET s.stockDisponible = s.stockDisponible - :qty WHERE s.id = :stickerId AND s.stockDisponible >= :qty")
    int reduceStockByQuantity(@Param("stickerId") Long stickerId, @Param("qty") int qty);

    /**
     * Disminuye en 1 el stock de todos los stickers cuyo id está en la lista. Devuelve el total de filas afectadas.
     * Permite un decremento bulk atómico en la base de datos (mejor rendimiento y menor número de queries).
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Sticker s SET s.stockDisponible = s.stockDisponible - 1 WHERE s.id IN :ids AND s.stockDisponible > 0")
    int reduceStockBulk(@Param("ids") List<Long> ids);
}