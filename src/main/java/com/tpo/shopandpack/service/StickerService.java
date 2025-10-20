package com.tpo.shopandpack.service;

import com.tpo.shopandpack.exception.BusinessException;
import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.repository.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StickerService {
    
    @Autowired
    private StickerRepository stickerRepository;
    
    public List<Sticker> findAll() {
        return stickerRepository.findAll();
    }
    
    public Sticker findById(Long id) {
        return stickerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sticker", "id", id));
    }
    
    public Sticker save(Sticker sticker) {
        validateSticker(sticker);
        return stickerRepository.save(sticker);
    }
    
    public Sticker update(Long id, Sticker stickerDetails) {
        Sticker sticker = findById(id);
        
        // Solo actualizar campos que no son relaciones críticas
        if (stickerDetails.getNombre() != null) {
            sticker.setNombre(stickerDetails.getNombre());
        }
        if (stickerDetails.getNumero() != null) {
            sticker.setNumero(stickerDetails.getNumero());
        }
        if (stickerDetails.getRareza() != null) {
            sticker.setRareza(stickerDetails.getRareza());
        }
        if (stickerDetails.getStockTotal() != null) {
            if (stickerDetails.getStockTotal() < 0) {
                throw new BusinessException("El stock total no puede ser negativo");
            }
            sticker.setStockTotal(stickerDetails.getStockTotal());
        }
        if (stickerDetails.getStockDisponible() != null) {
            if (stickerDetails.getStockDisponible() < 0) {
                throw new BusinessException("El stock disponible no puede ser negativo");
            }
            if (sticker.getStockTotal() != null && stickerDetails.getStockDisponible() > sticker.getStockTotal()) {
                throw new BusinessException("El stock disponible no puede ser mayor al stock total");
            }
            sticker.setStockDisponible(stickerDetails.getStockDisponible());
        }
        if (stickerDetails.getImagenUrl() != null) {
            sticker.setImagenUrl(stickerDetails.getImagenUrl());
        }
        
        return stickerRepository.save(sticker);
    }
    
    public void deleteById(Long id) {
        if (!stickerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sticker", "id", id);
        }
        stickerRepository.deleteById(id);
    }
    
    private void validateSticker(Sticker sticker) {
        if (sticker.getNombre() == null || sticker.getNombre().trim().isEmpty()) {
            throw new BusinessException("El nombre del sticker es obligatorio");
        }
        if (sticker.getAlbum() == null) {
            throw new BusinessException("El sticker debe pertenecer a un álbum");
        }
        if (sticker.getStockTotal() != null && sticker.getStockTotal() < 0) {
            throw new BusinessException("El stock total no puede ser negativo");
        }
        if (sticker.getStockDisponible() != null && sticker.getStockDisponible() < 0) {
            throw new BusinessException("El stock disponible no puede ser negativo");
        }
    }
}

