package com.example.productservice.client.fallback;


import com.example.productservice.client.InventoryClient;
import com.example.productservice.dto.InventoryResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    public List<InventoryResponse> isInStock(String skuCode) {
        return Arrays.stream(skuCode.split(","))
                .map(code -> new InventoryResponse(code , false))
                .toList();
    }
}
