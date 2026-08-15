package com.example.orderservice.mapper;


import com.example.orderservice.dto.OrderItemRequest;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public Order mapToOrder(OrderRequest orderRequest){

        Order order = new Order();
        List<OrderItem> orderItemList =
                orderRequest.getOrderItemRequestList().stream()
                        .map(this::mapToOrderItem)
                        .toList();


        order.setOrderItemList(orderItemList);
        return order;


    }



    public OrderItem mapToOrderItem (OrderItemRequest orderItemRequest){
        OrderItem orderItem = new OrderItem();

        return orderItem.builder()
                .skuCode(orderItemRequest.getSkuCode())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .build();
    }
}

