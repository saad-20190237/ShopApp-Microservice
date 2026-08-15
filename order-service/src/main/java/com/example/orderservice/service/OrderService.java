package com.example.orderservice.service;


import com.example.orderservice.dto.InventoryResponse;
import com.example.orderservice.dto.OrderItemRequest;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {


    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final WebClient.Builder webClientBuilder;



    public Long placeOrder(OrderRequest orderRequest) {

        List<String> skucodes = orderRequest.getOrderItemRequestList()
                .stream()
                .map(OrderItemRequest::getSkuCode)
                .toList();

         String skucodesJoined= String.join(",", skucodes);

        System.out.println("skucodesJoined= " + skucodesJoined);


            // call inventory service to check if the product in stock or not
            InventoryResponse[] response =
                    webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/inventory/{skuCode}" ,
                            skucodesJoined
                    )
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();


            


        Order order = new Order();
        order=orderMapper.mapToOrder(orderRequest);
        order.setOrderNumber(UUID.randomUUID().toString());
        Order savedOrder = orderRepo.save(order);

        return savedOrder.getId();







    }
}
