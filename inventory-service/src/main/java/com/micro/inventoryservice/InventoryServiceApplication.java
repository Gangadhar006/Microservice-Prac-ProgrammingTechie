package com.micro.inventoryservice;

import com.micro.inventoryservice.model.Inventory;
import com.micro.inventoryservice.repository.InventoryRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public ModelMapper getInstance() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepo) {
        return args -> {
            Inventory inventory1=new Inventory();
            inventory1.setSkuCode("xiaomi pad 6");
            inventory1.setQuantity(100);

            Inventory inventory2=new Inventory();
            inventory2.setSkuCode("samsung m32");
            inventory2.setQuantity(0);

            inventoryRepo.save(inventory1);
            inventoryRepo.save(inventory2);
        };
    }
}