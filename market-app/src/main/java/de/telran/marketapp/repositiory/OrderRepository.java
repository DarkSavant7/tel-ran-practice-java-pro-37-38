package de.telran.marketapp.repositiory;

import de.telran.marketapp.dto.AnotherOrderDto;
import de.telran.marketapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("""
              select new de.telran.marketapp.dto.AnotherOrderDto(o.id, o.price, o.created, o.updated) from Order o
            """)
    List<AnotherOrderDto> findAllDto();
}
