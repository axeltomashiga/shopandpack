package com.tpo.shopandpack.repository;

import com.tpo.shopandpack.emun.Rareza;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.model.Sticker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StickerRepository extends JpaRepository<Sticker, Long>{

    /**
     * Encuentra figuritas disponibles de un álbum (con stock > 0)
     */
    @Query("SELECT s FROM Sticker s WHERE s.album.id = :albumId AND s.stockDisponible > 0")
    List<Sticker> findAvailableByAlbumId(@Param("albumId") Long albumId);

    @Query("SELECT s FROM Sticker s WHERE s.rareza = :rareza AND s.album = :album")
    List<Sticker> findByRarezaAndAlbum(@Param("rareza") Rareza rareza, @Param("album") Album album);
}