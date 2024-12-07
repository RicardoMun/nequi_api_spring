package com.accenture.nequiApi.service;

import com.accenture.nequiApi.dto.ProductStockDTO;
import com.accenture.nequiApi.model.Franchise;
import com.accenture.nequiApi.model.Ofice;
import com.accenture.nequiApi.model.Product;
import com.accenture.nequiApi.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class ProductService {

    private final FranchiseRepository franchiseRepository;

    public ProductService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Product addProduct(String franchiseId, String oficeId, Product product) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        Ofice ofice = franchise.getOfices().stream()
                .filter(o -> o.getId().equals(oficeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ofice not found"));

        ofice.getProducts().add(product);
        franchiseRepository.save(franchise);

        return product;
    }

    public Product updateStock(String franchiseId, String oficeId, String productId, int stock) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        Ofice ofice = franchise.getOfices().stream()
                .filter(o -> o.getId().equals(oficeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ofice not found"));

        Product product = ofice.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(stock);
        franchiseRepository.save(franchise);

        return product;
    }

    public void deleteProduct(String franchiseId, String oficeId, String productId) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        Ofice ofice = franchise.getOfices().stream()
                .filter(o -> o.getId().equals(oficeId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ofice not found"));

        ofice.getProducts().removeIf(
                p -> p.getId().equals(productId)
        );
        franchiseRepository.save(franchise);
    }

    public List<ProductStockDTO> getMaxStock(String franchiseId) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        return franchise.getOfices().stream()
                .map(o -> o.getProducts().stream()
                        .max(Comparator.comparingInt(p -> {
                            if (p.getStock() < 0) {
                                throw new IllegalArgumentException("Stock cannot be negative");
                            }
                            return p.getStock();
                        }))
                        .map(product -> new ProductStockDTO(product.getName(), o.getName(), product.getStock()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
