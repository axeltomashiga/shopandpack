package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.model.UserSticker;
import com.tpo.shopandpack.service.UserStickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-stickers")
public class UserStickerController {
    
    @Autowired
    private UserStickerService userStickerService;
    
    /**
     * GET /api/user-stickers
     */
    @GetMapping
    public ResponseEntity<List<UserSticker>> getAllUserStickers() {
        List<UserSticker> userStickers = userStickerService.findAll();
        return ResponseEntity.ok(userStickers);
    }
    
    /**
     * GET /api/user-stickers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserSticker> getUserStickerById(@PathVariable Long id) {
        UserSticker userSticker = userStickerService.findById(id);
        return ResponseEntity.ok(userSticker);
    }
    
    /**
     * POST /api/user-stickers
     */
    @PostMapping
    public ResponseEntity<UserSticker> createUserSticker(@RequestBody UserSticker userSticker) {
        UserSticker savedUserSticker = userStickerService.save(userSticker);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserSticker);
    }
    
    /**
     * PUT /api/user-stickers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserSticker> updateUserSticker(@PathVariable Long id, @RequestBody UserSticker userSticker) {
        UserSticker updatedUserSticker = userStickerService.update(id, userSticker);
        return ResponseEntity.ok(updatedUserSticker);
    }
    
    /**
     * DELETE /api/user-stickers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserSticker(@PathVariable Long id) {
        userStickerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

