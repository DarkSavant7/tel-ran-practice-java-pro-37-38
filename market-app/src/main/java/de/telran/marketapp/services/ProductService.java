package de.telran.marketapp.services;

import de.telran.marketapp.dto.CreateProductDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.mapper.ProductMapper;
import de.telran.marketapp.repositiory.ProductRepository;
import jakarta.transaction.Transactional;
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
    ProductMapper mapper;

    @Transactional
    public ProductDto create(CreateProductDto dto) {
        log.info("Creating product");
        var product = mapper.mapCreateDtoToProduct(dto);
//        product.setId(UUID.randomUUID());
        var result = repository.save(product);
        return mapper.mapToDto(result);
    }

    @Transactional
    public ProductDto findById(UUID id) {
        log.info("Finding product {}", id);
        var result = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return mapper.mapToDto(result);
    }

    @Transactional
    public List<ProductDto> findAll() {
        log.info("Finding all products");
        return repository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Transactional
    public void deleteById(UUID id) {
        log.info("Deleting product {}", id);
        repository.deleteById(id);
    }
}
