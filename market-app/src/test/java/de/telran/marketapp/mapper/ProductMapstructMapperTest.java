package de.telran.marketapp.mapper;

import de.telran.marketapp.util.FakeHolder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ExtendWith(MockitoExtension.class)
public class ProductMapstructMapperTest {

    ProductMapstructMapper mapstructMapper;

//    @BeforeEach
//    public void initMapper() {
//        this.mapstructMapper = new ProductMapstructMapperImpl();
//    }

    @Test
    public void mapToDtoTest() {
        this.mapstructMapper = new ProductMapstructMapperImpl();
        var product = FakeHolder.referenceProduct;
        var mappedDto = mapstructMapper.mapToDto(product);
        var referenceDto = FakeHolder.referenceProductDto;
        assertEquals(referenceDto, mappedDto);
    }
}
