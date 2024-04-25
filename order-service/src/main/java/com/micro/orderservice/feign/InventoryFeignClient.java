package com.micro.orderservice.feign;

import com.micro.orderservice.payload.response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryFeignClient {
    @GetMapping("/api/inventory/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    List<InventoryResponse> isInStock(@PathVariable("sku-code") List<String> skuCode);
}