package com.example.inventory_service;

import com.example.inventory_service.model.Inventory;
import com.example.inventory_service.repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(InventoryRepo inventoryRepo) {
        return args -> {
            if(!inventoryRepo.existsBySkuCode("SKU1")){
                Inventory  inventory1 = new Inventory();
                inventory1.setSkuCode("SKU1");
                inventory1.setQuantity(1);
                inventoryRepo.save(inventory1);

            }

            if(!inventoryRepo.existsBySkuCode("SKU2")){
                Inventory  inventory2 = new Inventory();
                inventory2.setSkuCode("SKU2");
                inventory2.setQuantity(2);
                inventoryRepo.save(inventory2);
            }

        };

    }
}
