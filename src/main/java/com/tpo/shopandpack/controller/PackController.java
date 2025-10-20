package com.tpo.shopandpack.controller;

import com.tpo.shopandpack.model.Pack;
import com.tpo.shopandpack.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packs")
public class PackController {
    
    @Autowired
    private PackService packService;
    
    /**
     * GET /api/packs
     */
    @GetMapping
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> packs = packService.findAll();
        return ResponseEntity.ok(packs);
    }
    
    /**
     * GET /api/packs/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        Pack pack = packService.findById(id);
        return ResponseEntity.ok(pack);
    }
    
    /**
     * POST /api/packs
     */
    @PostMapping
    public ResponseEntity<Pack> createPack(@RequestBody Pack pack) {
        Pack savedPack = packService.save(pack);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPack);
    }
    
    /**
     * PUT /api/packs/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @RequestBody Pack pack) {
        Pack updatedPack = packService.update(id, pack);
        return ResponseEntity.ok(updatedPack);
    }
    
    /**
     * DELETE /api/packs/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

