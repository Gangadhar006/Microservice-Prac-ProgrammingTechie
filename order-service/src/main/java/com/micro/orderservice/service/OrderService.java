package com.micro.orderservice.service;

import com.micro.orderservice.feign.InventoryFeignClient;
import com.micro.orderservice.model.Order;
import com.micro.orderservice.model.OrderItems;
import com.micro.orderservice.payload.OrderItemsDto;
import com.micro.orderservice.payload.request.OrderRequest;
import com.micro.orderservice.payload.response.InventoryResponse;
import com.micro.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepo;
    private final InventoryFeignClient inventoryFeignClient;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderItems> orderItems = orderRequest.getOrderItemsDtos().stream().map(this::mapToDto).toList();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderItems(orderItems);

        List<String> skuCodes = orderItems.stream()
                .map(OrderItems::getSkuCode)
                .toList();


        // call inventory service, and place order if it is in stock
        // order-service requesting inventory-service for stock availability
        List<InventoryResponse> inventoryResponseArray = inventoryFeignClient.isInStock(skuCodes);

        boolean allProductsIsInStock = inventoryResponseArray.stream().allMatch(InventoryResponse::isInStock);

        if (allProductsIsInStock) {
            orderRepo.save(order);
            return "order placed successfully..!";
        }
        else
            throw new IllegalArgumentException("product is not in stock");
    }

    public OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        return OrderItems.builder()
                .skuCode(orderItemsDto.getSkuCode())
                .price(orderItemsDto.getPrice())
                .quantity(orderItemsDto.getQuantity())
                .build();
    }
}