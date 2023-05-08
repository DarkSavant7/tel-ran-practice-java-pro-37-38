package de.telran.marketapp.repositiory;

import de.telran.marketapp.dto.OrderItemFlatDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderItemCustomRepository {
    private static final String SQL_REQUEST = """
            select BIN_TO_UUID(item.id, true) as id, item.quantity as quantity, item.entire_price as entirePrice,
            BIN_TO_UUID(p.id, true) as productId, p.name as productName, p.description as productDescription,
            p.price as currentProductPrice, BIN_TO_UUID(item.order_id, true) as orderId
            from order_items item
            left join products p on p.id = item.product_id
            where item.entire_price >= :price                    
            """;

    NamedParameterJdbcTemplate jdbcTemplate;

    public List<OrderItemFlatDto> getOrderItems(BigDecimal price) {
        return jdbcTemplate.queryForStream(SQL_REQUEST, Map.of("price", price), (rs, rowNum) -> new OrderItemFlatDto(
                UUID.fromString(rs.getString("id")),
                rs.getBigDecimal("quantity"),
                rs.getBigDecimal("entirePrice"),
                UUID.fromString(rs.getString("productId")),
                rs.getString("productName"),
                rs.getString("productDescription"),
                rs.getBigDecimal("currentProductPrice"),
                UUID.fromString(rs.getString("orderId"))
        )).toList();
    }
}
