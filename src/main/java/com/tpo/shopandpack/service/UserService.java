package com.tpo.shopandpack.service;

import com.tpo.shopandpack.exception.BusinessException;
import com.tpo.shopandpack.exception.ResourceNotFoundException;
import com.tpo.shopandpack.model.User;
import com.tpo.shopandpack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }
    
    public Optional<User> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new BusinessException("El username no puede estar vacío");
        }
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException("El email no puede estar vacío");
        }
        return userRepository.findByEmail(email);
    }
    
    public User save(User user) {
        validateUser(user);
        
        // Verificar que username no exista
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BusinessException("Ya existe un usuario con username: " + user.getUsername());
        }
        
        // Verificar que email no exista
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BusinessException("Ya existe un usuario con email: " + user.getEmail());
        }
        
        return userRepository.save(user);
    }
    
    public User update(Long id, User userDetails) {
        User user = findById(id);
        
        // Username es inmutable, no se actualiza
        
        // Email solo si es diferente y no existe
        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
                throw new BusinessException("Ya existe un usuario con email: " + userDetails.getEmail());
            }
            user.setEmail(userDetails.getEmail());
        }
        
        if (userDetails.getTelefono() != null) {
            user.setTelefono(userDetails.getTelefono());
        }
        if (userDetails.getNombre() != null) {
            user.setNombre(userDetails.getNombre());
        }
        if (userDetails.getApellido() != null) {
            user.setApellido(userDetails.getApellido());
        }
        if (userDetails.getRole() != null) {
            user.setRole(userDetails.getRole());
        }
        if (userDetails.getHashPassword() != null) {
            user.setHashPassword(userDetails.getHashPassword());
        }
        if (userDetails.getAvatarUrl() != null) {
            user.setAvatarUrl(userDetails.getAvatarUrl());
        }
        
        return userRepository.save(user);
    }
    
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario", "id", id);
        }
        userRepository.deleteById(id);
    }
    
    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new BusinessException("El username es obligatorio");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new BusinessException("El email es obligatorio");
        }
        if (!user.getEmail().contains("@")) {
            throw new BusinessException("El email no tiene un formato válido");
        }
    }
}

