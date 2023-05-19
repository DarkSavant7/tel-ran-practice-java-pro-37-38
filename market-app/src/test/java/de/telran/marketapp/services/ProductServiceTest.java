package de.telran.marketapp.services;

import de.telran.marketapp.mapper.ProductMapper;
import de.telran.marketapp.mapper.ProductMapstructMapper;
import de.telran.marketapp.repositiory.ProductRepository;
import de.telran.marketapp.util.FakeHolder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        var id = UUID.fromString("64fb5b85-d01f-4e74-b5ea-865a1084da9e");
        when(repository.findById(id)).thenReturn(Optional.empty());
        var result = assertThrows(NotFoundException.class,
                () -> productService.getById(id),
                "Product not found. Id: 64fb5b85-d01f-4e74-b5ea-865a1084da9e"
        );
        assertEquals("Product not found. Id: 64fb5b85-d01f-4e74-b5ea-865a1084da9e", result.getMessage());
        assertThat(result)
                .hasNoCause()
                .hasMessage("Product not found. Id: 64fb5b85-d01f-4e74-b5ea-865a1084da9e")
                .isNotEqualTo(new Object())
                .isInstanceOf(NotFoundException.class);

    }

    @Test
    void findAll() {
//        for (int i = 1; i < 3; i++)
//            for (int j = 3; j >= 1; j--)
//                assert i != j : i;
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