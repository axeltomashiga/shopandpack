package com.tpo.shopandpack.service;

import com.tpo.shopandpack.exception.BusinessException;
import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PackService {
    
    @Autowired
    private PackageRepository packRepository;
    
    public List<Pack> findAll() {
        return packRepository.findAll();
    }
    
    public Pack findById(Long id) {
        return packRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pack", "id", id));
    }
    
    public Pack save(Pack pack) {
        validatePack(pack);
        return packRepository.save(pack);
    }
    
    public Pack update(Long id, Pack packDetails) {
        Pack pack = findById(id);
        
        // Los packs generalmente no se actualizan, pero si se permite:
        // No se debe cambiar user ni album una vez creado (lógica de negocio)
        // Solo permitimos actualizar si explícitamente se envía
        
        if (packDetails.getUser() != null && !packDetails.getUser().equals(pack.getUser())) {
            throw new BusinessException("No se puede cambiar el usuario de un pack ya creado");
        }
        
        if (packDetails.getAlbum() != null && !packDetails.getAlbum().equals(pack.getAlbum())) {
            throw new BusinessException("No se puede cambiar el álbum de un pack ya creado");
        }
        
        // Si en el futuro se necesita actualizar stickers, se haría aquí
        
        return packRepository.save(pack);
    }
    
    public void deleteById(Long id) {
        if (!packRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pack", "id", id);
        }
        packRepository.deleteById(id);
    }
    
    private void validatePack(Pack pack) {
        if (pack.getUser() == null) {
            throw new BusinessException("El pack debe tener un usuario asignado");
        }
        if (pack.getAlbum() == null) {
            throw new BusinessException("El pack debe pertenecer a un álbum");
        }
    }
}

