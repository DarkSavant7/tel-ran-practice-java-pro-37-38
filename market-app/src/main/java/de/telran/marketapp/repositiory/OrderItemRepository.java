package de.telran.marketapp.repositiory;

import de.telran.marketapp.dto.OrderItemFlatDto;
import de.telran.marketapp.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Query("""
            select new de.telran.marketapp.dto.OrderItemFlatDto(i.id, i.quantity, i.entirePrice, p.id, p.name, p.description, p.price, i.order.id)
            from OrderItem i
            left join Product p on p.id = i.product.id                    
            """
    )
    List<OrderItemFlatDto> findAllDto();
}
