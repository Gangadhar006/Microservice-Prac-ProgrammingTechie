package com.micro.inventoryservice;

import com.micro.inventoryservice.model.Inventory;
import com.micro.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepo) {
        return args -> {
            Inventory inventory1 = new Inventory();
            inventory1.setSkuCode("Xiamoi Pad 6");
            inventory1.setQuantity(10);

            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("Realme 7");
            inventory2.setQuantity(15);

            inventoryRepo.save(inventory1);
            inventoryRepo.save(inventory2);
        };
    }
}