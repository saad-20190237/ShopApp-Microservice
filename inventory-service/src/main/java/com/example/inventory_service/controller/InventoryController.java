package com.example.inventory_service.controller;


import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public ResponseEntity<List<InventoryResponse>> isInStock(
            @PathVariable String skuCode){

       List<String> codes =
               Arrays.asList(skuCode.split(","));



          List<InventoryResponse> results =  inventoryService.isInStock(codes);

          return
                  ResponseEntity.ok(results);
    }
}
