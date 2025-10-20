package com.tpo.shopandpack.service;

import com.tpo.shopandpack.exception.BusinessException;
import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.UserSticker;
import com.tpo.shopandpack.repository.UserStickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserStickerService {
    
    @Autowired
    private UserStickerRepository userStickerRepository;
    
    public List<UserSticker> findAll() {
        return userStickerRepository.findAll();
    }
    
    public UserSticker findById(Long id) {
        return userStickerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserSticker", "id", id));
    }
    
    public UserSticker save(UserSticker userSticker) {
        validateUserSticker(userSticker);
        return userStickerRepository.save(userSticker);
    }
    
    public UserSticker update(Long id, UserSticker userStickerDetails) {
        UserSticker userSticker = findById(id);
        
        // Solo permitimos cambiar el estado, no el user ni el sticker
        if (userStickerDetails.getEstado() != null) {
            userSticker.setEstado(userStickerDetails.getEstado());
        }
        
        return userStickerRepository.save(userSticker);
    }
    
    public void deleteById(Long id) {
        if (!userStickerRepository.existsById(id)) {
            throw new ResourceNotFoundException("UserSticker", "id", id);
        }
        userStickerRepository.deleteById(id);
    }
    
    private void validateUserSticker(UserSticker userSticker) {
        if (userSticker.getUser() == null) {
            throw new BusinessException("UserSticker debe tener un usuario asignado");
        }
        if (userSticker.getSticker() == null) {
            throw new BusinessException("UserSticker debe tener un sticker asignado");
        }
    }
}

