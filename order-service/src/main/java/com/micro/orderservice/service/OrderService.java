package com.micro.orderservice.service;

import com.micro.orderservice.model.Order;
import com.micro.orderservice.model.OrderItems;
import com.micro.orderservice.payload.OrderItemsDto;
import com.micro.orderservice.payload.request.OrderRequest;
import com.micro.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepo;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        List<OrderItems> orderItems = orderRequest.getOrderItemsDtos().stream().map(this::mapToDto).toList();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderItems(orderItems);
        orderRepo.save(order);
    }

    public OrderItems mapToDto(OrderItemsDto orderItemsDto) {
        return OrderItems.builder()
                .skuCode(orderItemsDto.getSkuCode())
                .price(orderItemsDto.getPrice())
                .quantity(orderItemsDto.getQuantity())
                .build();
    }
}