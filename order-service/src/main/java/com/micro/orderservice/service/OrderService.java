package com.micro.orderservice.service;

import com.micro.orderservice.feign.InventoryFeignClient;
import com.micro.orderservice.model.Order;
import com.micro.orderservice.model.OrderItems;
import com.micro.orderservice.payload.InventoryResponse;
import com.micro.orderservice.payload.OrderItemsDto;
import com.micro.orderservice.payload.OrderRequest;
import com.micro.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final ModelMapper mapper;
    private final OrderRepository orderRepo;
    //    private final WebClient.Builder webClient;
    private final InventoryFeignClient inventoryFeignClient;

    public String placeOrder(OrderRequest orderRequest) {
        List<OrderItemsDto> orderItemsDto = orderRequest.getOrderItems();
        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderItems(orderItemsDto.stream()
                .map(orderItemDto -> mapper.map(orderItemDto, OrderItems.class))
                .toList());

        //call inventory service & place order if it is stock
        List<String> skuCodes = order.getOrderItems().stream()
                .map(OrderItems::getSkuCode)
                .toList();

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
}