package de.telran.marketapp.repositiory;

import de.telran.marketapp.entities.Order;
import de.telran.marketapp.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
