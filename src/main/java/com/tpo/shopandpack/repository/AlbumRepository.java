package com.tpo.shopandpack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpo.shopandpack.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{
    
    java.util.Optional<Album> findByTitulo(String titulo);
}
