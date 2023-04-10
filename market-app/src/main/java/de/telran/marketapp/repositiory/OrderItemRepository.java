package de.telran.marketapp.repositiory;

import de.telran.marketapp.dto.OrderItemFlatDto;
import de.telran.marketapp.dto.OrderItemFlatProjection;
import de.telran.marketapp.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
//TODO show criteria API
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Query("""
            select new de.telran.marketapp.dto.OrderItemFlatDto(i.id, i.quantity, i.entirePrice, p.id, p.name, p.description, p.price, i.order.id)
            from OrderItem i
            left join Product p on p.id = i.product.id                    
            """
    )
    List<OrderItemFlatDto> findAllDto();

    @Query(value = """
            select BIN_TO_UUID(item.id, true) as id, item.quantity as quantity, item.entire_price as entirePrice,
            BIN_TO_UUID(p.id, true) as productId, p.name as productName, p.description as productDescription,
            p.price as currentProductPrice, BIN_TO_UUID(item.order_id, true) as orderId
            from order_items item
            left join products p on p.id = item.product_id
            where item.entire_price >= :price                    
            """, nativeQuery = true
    )
    List<OrderItemFlatProjection> findAllDtoAnotherWay(@Param("price") BigDecimal price);

    @Query("""
            select OrderItem
            from OrderItem i                 
            """
    )
    List<OrderItem> findAllItems();
}
