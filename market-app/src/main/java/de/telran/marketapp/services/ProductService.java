package de.telran.marketapp.services;

import de.telran.marketapp.dto.CreateProductDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.mapper.ProductMapper;
import de.telran.marketapp.mapper.ProductMapstructMapper;
import de.telran.marketapp.repositiory.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductService {

    ProductRepository repository;
    ProductMapper productMapper;
    ProductMapstructMapper productMapstructMapper;

    @Transactional
    public ProductDto create(CreateProductDto dto) {
        log.info("Creating product");
        var product = productMapper.mapCreateDtoToProduct(dto);
        var result = repository.save(product);
        return productMapstructMapper.mapToDto(result);
    }

    @Transactional
    public ProductDto findById(UUID id) {
        log.info("Finding product {}", id);
        var result = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return productMapstructMapper.mapToDto(result);
//        var response = productMapstructMapper.mapToDto(result);
//        response.setPrice();
//        return response;
    }

    @Transactional
    public Product getById(UUID id) {
        log.info("Finding product {}", id);
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public Page<ProductDto> findAll(int page, int size) {
        log.info("Finding all products");
        var pagination = PageRequest.of(page, size);
        return repository.findAll(pagination).map(productMapstructMapper::mapToDto);
    }

    @Transactional
    public List<Product> findByIds(Collection<UUID> ids) {
        return repository.findAllByIdIn(ids);
    }

    @Transactional
    public void deleteById(UUID id) {
        log.info("Deleting product {}", id);
        repository.deleteById(id);
    }
}
