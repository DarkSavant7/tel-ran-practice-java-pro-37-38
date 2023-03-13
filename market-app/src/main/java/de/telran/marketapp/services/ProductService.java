package de.telran.marketapp.services;

import de.telran.marketapp.entities.Product;
import de.telran.marketapp.repositiory.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService {

    ProductRepository repository;

    public Product create(Product product) {
        log.info("Creating product");
        product.setId(UUID.randomUUID());
        return repository.save(product);
    }

    public Product findById(UUID id) {
        log.info("Finding product {}", id);
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public List<Product> findAll() {
        log.info("Finding all products");
        return repository.findAll();
    }

    public void deleteById(UUID id) {
        log.info("Deleting product {}", id);
        repository.deleteById(id);
    }
}
