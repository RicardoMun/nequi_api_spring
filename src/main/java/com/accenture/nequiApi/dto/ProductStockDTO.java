package com.accenture.nequiApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProductStockDTO {

    private String productName;
    private String oficeName;
    private int stock;

    public ProductStockDTO(String productName, String oficeName, int stock) {
        this.productName = productName;
        this.oficeName = oficeName;
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOficeName() {
        return oficeName;
    }

    public void setOficeName(String oficeName) {
        this.oficeName = oficeName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
