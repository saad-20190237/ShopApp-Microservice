package com.example.productservice.client;


import com.example.productservice.client.fallback.InventoryClientFallback;
import com.example.productservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "inventory-service",
        fallback = InventoryClientFallback.class
)
public interface InventoryClient {


    @GetMapping("/api/inventory/{skuCode}")
    List<InventoryResponse> isInStock(
            @PathVariable String skuCode
    );
}
