package com.example.inventory_service.service;


import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.model.Inventory;
import com.example.inventory_service.repository.InventoryRepo;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo  inventoryRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodeList) {

        List<Inventory> inventories = inventoryRepo.findBySkuCodeIn(skuCodeList);
        List<InventoryResponse> results = new ArrayList<>();

        for (String skuCode : skuCodeList) {
            boolean inStock = inventories.stream()
                    .anyMatch(inventory ->
                            inventory.getSkuCode().equals(skuCode)
                                    && inventory.getQuantity() > 0
                    );
            results.add(new InventoryResponse(skuCode, inStock));


        }


        return results;
    }

}
