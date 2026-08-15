package com.example.productservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryResponse {

    private String skuCode;
    private boolean inStock;
}
