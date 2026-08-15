package com.example.inventory_service;

import com.example.inventory_service.controller.InventoryController;
import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.repository.InventoryRepo;
import com.example.inventory_service.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(InventoryController.class)
class InventoryControllerTest {

    @MockitoBean
    private InventoryService inventoryService;

    @MockitoBean
    private InventoryRepo inventoryRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }


    //WebMVCTest
    @Test
    void shouldReturnInventoryStatus() throws Exception {

        List<InventoryResponse> responses= List.of(
                new InventoryResponse("SKU1",true)
        );

        when(inventoryService.isInStock(List.of("SKU1")))
                .thenReturn(responses);

        mockMvc.perform(
                        get("/api/inventory/SKU1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].skuCode").value("SKU1"));



    }

}
