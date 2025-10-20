package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.model.Sticker;
import com.tpo.shopandpack.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stickers")
public class StickerController {
    
    @Autowired
    private StickerService stickerService;
    
    /**
     * GET /api/stickers
     */
    @GetMapping
    public ResponseEntity<List<Sticker>> getAllStickers() {
        List<Sticker> stickers = stickerService.findAll();
        return ResponseEntity.ok(stickers);
    }
    
    /**
     * GET /api/stickers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sticker> getStickerById(@PathVariable Long id) {
        Sticker sticker = stickerService.findById(id);
        return ResponseEntity.ok(sticker);
    }
    
    /**
     * POST /api/stickers
     */
    @PostMapping
    public ResponseEntity<Sticker> createSticker(@RequestBody Sticker sticker) {
        Sticker savedSticker = stickerService.save(sticker);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSticker);
    }
    
    /**
     * PUT /api/stickers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sticker> updateSticker(@PathVariable Long id, @RequestBody Sticker sticker) {
        Sticker updatedSticker = stickerService.update(id, sticker);
        return ResponseEntity.ok(updatedSticker);
    }
    
    /**
     * DELETE /api/stickers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSticker(@PathVariable Long id) {
        stickerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

