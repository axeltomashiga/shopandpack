package com.tpo.shopandpack.repository;

import com.tpo.shopandpack.model.Pack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PackageRepository extends JpaRepository<Pack, Long>{

    @Query("SELECT COUNT(p) FROM Pack p WHERE p.user.id = :userId AND p.album.id = :albumId")
    long countByUserIdAndAlbumId(@Param("userId") Long userId, @Param("albumId") Long albumId);
}