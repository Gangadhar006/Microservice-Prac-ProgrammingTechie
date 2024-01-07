package com.micro.productservice.service;

import com.micro.productservice.model.Product;
import com.micro.productservice.payload.ProductRequest;
import com.micro.productservice.payload.ProductResponse;
import com.micro.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepo;
    private final ModelMapper mapper;

    public void createProduct(ProductRequest productRequest) {
        Product product = mapper.map(productRequest, Product.class);
        productRepo.save(product);
        log.info("product: {} is saved", product.getId());
    }

    public List<ProductResponse> fetchProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .toList();
    }
}