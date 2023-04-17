package de.telran.marketapp.util;

import de.telran.marketapp.dto.CreateProductDto;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;

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
}
