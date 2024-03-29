package de.telran.marketapp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class AnotherOrderDto {
    UUID id;
    List<OrderItemFlatDto> items;
    BigDecimal price;
    OffsetDateTime created;
    OffsetDateTime updated;

    public AnotherOrderDto(UUID id, BigDecimal price, OffsetDateTime created, OffsetDateTime updated) {
        this.id = id;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }
}
