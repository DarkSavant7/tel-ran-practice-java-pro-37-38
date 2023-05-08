package de.telran.marketapp.util;

import de.telran.marketapp.dto.CreateProductDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.entities.Product;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class FakeHolder {

    public CreateProductDto createProductDto = new CreateProductDto(
            "PRODUCT",
            "Product description",
            BigDecimal.TEN,
            List.of("food")
    );

    public final String CREATE_PRODUCT_REQUEST = """
            {
              "name": "test-prod",
              "description": "test-description",
              "price": 10.0,
              "tags": [
              ]
            }
            """;

    public final String CREATE_PRODUCT_RESPONSE = """
            {
              "id": "b09fb1df-10af-49b9-ad6c-11341690418e",
              "name": "test-prod",
              "description": "test-description",
              "price": 10,
              "tags": []
            }
            """;

    public final Product referenceProduct = Product.builder()
            .id(UUID.fromString("e49b4456-a62e-46b2-aa03-ecf13e9a1b01"))
            .price(BigDecimal.TEN)
            .tags(Collections.emptySet())
            .description("Test description")
            .name("Test product")
            .created(OffsetDateTime.of(2023, 4, 19, 5, 2, 0, 0, ZoneOffset.UTC))
            .updated(OffsetDateTime.of(2023, 4, 19, 7, 2, 0, 0, ZoneOffset.UTC))
            .build();

    public final ProductDto referenceProductDto = ProductDto.builder()
            .id(UUID.fromString("e49b4456-a62e-46b2-aa03-ecf13e9a1b01"))
            .price(BigDecimal.TEN)
            .tags(Collections.emptyList())
            .description("Test description")
            .name("Test product")
            .build();

    public final String FAKE_RESPONCE = """
            {
              "id": "b09fb1df-10af-49b9-ad6c-11341690418e",
              "price": 10
            }
            """;
}
