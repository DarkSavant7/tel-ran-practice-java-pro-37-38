package de.telran.marketapp.services;

import de.telran.marketapp.mapper.ProductMapper;
import de.telran.marketapp.mapper.ProductMapstructMapper;
import de.telran.marketapp.repositiory.ProductRepository;
import de.telran.marketapp.util.FakeHolder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository repository;
    @Mock
    ProductMapper productMapper;
    @Mock
    ProductMapstructMapper productMapstructMapper;

//    @BeforeEach
//    void init() {
//        productService = new ProductService(repository, productMapper, productMapstructMapper);
//    }


    @Test
    void create() {
        productService.create(FakeHolder.createProductDto);
        verify(repository, times(1)).save(any());
        verify(productMapper, times(1)).mapCreateDtoToProduct(any());
        verify(productMapstructMapper, times(1)).mapToDto(any());
    }

    @Test
    void findById() {
    }

    @Test
    void getById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByIds() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void methodForAspect() {
    }
}