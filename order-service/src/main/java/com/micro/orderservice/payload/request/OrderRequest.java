package com.micro.orderservice.payload.request;

import com.micro.orderservice.model.OrderItems;
import com.micro.orderservice.payload.OrderItemsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderItemsDto> orderItemsDtos;
}
