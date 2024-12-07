package com.accenture.nequiApi.controller;

import com.accenture.nequiApi.dto.ProductStockDTO;
import com.accenture.nequiApi.model.Franchise;
import com.accenture.nequiApi.model.Ofice;
import com.accenture.nequiApi.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping
    public ResponseEntity<?> createFranchise(@RequestBody Franchise franchise) {
        Franchise savedFranchise = franchiseService.saveFranchise(franchise);
        if (savedFranchise == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving franchise");
        }
        return ResponseEntity.ok(savedFranchise);
    }

    @PostMapping("/{franchiseId}/ofices")
    public ResponseEntity<Ofice> addOfice(@PathVariable String franchiseId, @RequestBody Ofice ofice) {
        return ResponseEntity.ok(franchiseService.addOfice(franchiseId, ofice));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Franchise> updateName(@PathVariable String id, @RequestParam String name) {
        return ResponseEntity.ok(franchiseService.updateName(id, name));
    }

    @GetMapping("/{franchiseId}/products-max-stock")
    public ResponseEntity<List<ProductStockDTO>> getProductMaxStock(@PathVariable String franchiseId) {
        return ResponseEntity.ok(franchiseService.getProductMaxStock(franchiseId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseService.getAllFranchises();
        return ResponseEntity.ok(franchises);
    }



}
