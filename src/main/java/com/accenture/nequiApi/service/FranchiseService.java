package com.accenture.nequiApi.service;

import com.accenture.nequiApi.dto.ProductStockDTO;
import com.accenture.nequiApi.model.Franchise;
import com.accenture.nequiApi.model.Ofice;
import com.accenture.nequiApi.model.Product;
import com.accenture.nequiApi.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    public Franchise saveFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Franchise updateName(String id, String newName) {

        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        franchise.setName(newName);

        return franchiseRepository.save(franchise);
    }

    public Ofice addOfice(String franchiseId, Ofice ofice) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        franchise.getOfices().add(ofice);
        franchiseRepository.save(franchise);

        return ofice;
    }

    public List<ProductStockDTO> getProductMaxStock(String franchiseId) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));

        return franchise.getOfices().stream()
                .map(ofice -> ofice.getProducts().stream()
                        .max(Comparator.comparingInt(Product::getStock))
                        .map(product -> new ProductStockDTO(ofice.getName(), product.getName(), product.getStock()))
                        .orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
