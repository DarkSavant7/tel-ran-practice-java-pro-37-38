package de.telran.marketapp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class OrderItemFlatDto implements OrderItemFlatProjection {
    UUID id;
    BigDecimal quantity;
    BigDecimal entirePrice;
    UUID productId;
    String productName;
    String productDescription;
    BigDecimal currentProductPrice;
    UUID orderId;
}
