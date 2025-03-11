package com.clothingmarketplace.api.controller;

import com.clothingmarketplace.api.entity.Merchant;
import com.clothingmarketplace.api.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {
    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        Merchant newMerchant = merchantService.create(merchant);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMerchant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchant(@PathVariable String id) {
        Merchant merchant = merchantService.getById(id);
        return ResponseEntity.ok(merchant);
    }

    @GetMapping
    public ResponseEntity<List<Merchant>> getMerchants() {
        List<Merchant> merchants = merchantService.getAll();
        return ResponseEntity.ok(merchants);
    }

    @PutMapping
    public ResponseEntity<Merchant> updateMerchant(@RequestBody Merchant merchant) {
        Merchant updatedMerchant = merchantService.update(merchant);
        return ResponseEntity.ok(updatedMerchant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Merchant> deleteMerchant(@PathVariable String id) {
        merchantService.delete(id);
        return ResponseEntity.ok().build();
    }
}
