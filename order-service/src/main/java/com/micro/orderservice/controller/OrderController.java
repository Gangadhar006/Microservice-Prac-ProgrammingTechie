package com.micro.orderservice.controller;

import com.micro.orderservice.payload.request.OrderRequest;
import com.micro.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order placed successfully!!";
    }

    @GetMapping
    public String test(){
        return "<h1>It's Working!</h1>";
    }
}
