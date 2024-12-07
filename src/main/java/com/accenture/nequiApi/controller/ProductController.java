package com.accenture.nequiApi.controller;

import com.accenture.nequiApi.dto.ProductStockDTO;
import com.accenture.nequiApi.model.Product;
import com.accenture.nequiApi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private final ProductService productService;

    @PostMapping("/{franchiseId}/{oficeId}")
    public ResponseEntity<Product> addProduct(@PathVariable String franchiseId, @PathVariable String oficeId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(franchiseId, oficeId, product));
    }

    @DeleteMapping("/{franchiseId}/{oficeId}/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String franchiseId, @PathVariable String oficeId, @PathVariable String productId) {
        productService.deleteProduct(franchiseId, oficeId, productId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{franchiseId}/{oficeId}/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable String franchiseId, @PathVariable String oficeId, @PathVariable String productId, @RequestParam int stock) {
        return ResponseEntity.ok(productService.updateStock(franchiseId, oficeId, productId, stock));
    }

    @GetMapping("/{franchiseId}/max-stock")
    public ResponseEntity<List<ProductStockDTO>> getMaxStock(@PathVariable String franchiseId) {
        return ResponseEntity.ok(productService.getMaxStock(franchiseId));
    }

}
