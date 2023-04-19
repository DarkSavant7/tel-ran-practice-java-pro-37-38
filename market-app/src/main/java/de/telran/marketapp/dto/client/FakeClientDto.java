package de.telran.marketapp.dto.client;

import de.telran.marketapp.dto.OrderItemFlatDto;
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
public class FakeClientDto {
    UUID id;
    BigDecimal price;
//    OffsetDateTime created;
//    OffsetDateTime updated;
}
