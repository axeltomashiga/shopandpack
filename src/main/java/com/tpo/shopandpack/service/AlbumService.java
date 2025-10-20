package com.tpo.shopandpack.service;

import com.tpo.shopandpack.exception.BusinessException;
import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.Album;
import com.tpo.shopandpack.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;
    
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    
    public Album findById(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album", "id", id));
    }
    
    public List<Album> findByPublicado(Boolean publicado) {
        if (publicado == null) {
            return albumRepository.findAll();
        }
        return albumRepository.findByPublicado(publicado);
    }
    
    public List<Album> findByCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new BusinessException("La categoría no puede estar vacía");
        }
        return albumRepository.findByCategoria(categoria);
    }
    
    public Album save(Album album) {
        if (album.getTitulo() == null || album.getTitulo().trim().isEmpty()) {
            throw new BusinessException("El título del álbum es obligatorio");
        }
        if (album.getTotalFiguritas() != null && album.getTotalFiguritas() <= 0) {
            throw new BusinessException("El total de figuritas debe ser mayor a 0");
        }
        return albumRepository.save(album);
    }
    
    public Album update(Long id, Album albumDetails) {
        Album album = findById(id);
        
        // Solo actualizar campos que no son inmutables
        if (albumDetails.getTitulo() != null) {
            album.setTitulo(albumDetails.getTitulo());
        }
        if (albumDetails.getDescripcion() != null) {
            album.setDescripcion(albumDetails.getDescripcion());
        }
        if (albumDetails.getCategoria() != null) {
            album.setCategoria(albumDetails.getCategoria());
        }
        if (albumDetails.getDificultad() != null) {
            album.setDificultad(albumDetails.getDificultad());
        }
        if (albumDetails.getTotalFiguritas() != null) {
            if (albumDetails.getTotalFiguritas() <= 0) {
                throw new BusinessException("El total de figuritas debe ser mayor a 0");
            }
            album.setTotalFiguritas(albumDetails.getTotalFiguritas());
        }
        if (albumDetails.getPublicado() != null) {
            album.setPublicado(albumDetails.getPublicado());
        }
        
        return albumRepository.save(album);
    }
    
    public void deleteById(Long id) {
        if (!albumRepository.existsById(id)) {
            throw new ResourceNotFoundException("Album", "id", id);
        }
        albumRepository.deleteById(id);
    }
}

