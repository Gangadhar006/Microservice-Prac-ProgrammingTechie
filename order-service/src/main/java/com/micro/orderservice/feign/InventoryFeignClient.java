package com.micro.orderservice.feign;

import com.micro.orderservice.payload.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryFeignClient {
    @GetMapping("/api/inventory")
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);
}
