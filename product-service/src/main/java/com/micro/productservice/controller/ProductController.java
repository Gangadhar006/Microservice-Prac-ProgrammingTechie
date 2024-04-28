package com.micro.productservice.controller;

import com.micro.productservice.payload.ProductRequest;
import com.micro.productservice.payload.ProductResponse;
import com.micro.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> fetchProducts(){
        return productService.fetchProducts();
    }

    @GetMapping("/test")
    public String test() {
        return "<h1>Product Service Test</h1>";
    }
}