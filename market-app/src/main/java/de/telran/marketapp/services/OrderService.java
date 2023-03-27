package de.telran.marketapp.services;

import de.telran.marketapp.dto.CreateOrderDto;
import de.telran.marketapp.dto.OrderDto;
import de.telran.marketapp.entities.Order;
import de.telran.marketapp.entities.OrderItem;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.mapper.OrderMapper;
import de.telran.marketapp.repositiory.OrderItemRepository;
import de.telran.marketapp.repositiory.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService {

    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderMapper mapper;
    ProductService productService;

    @Transactional
    public OrderDto createOrder(CreateOrderDto dto) {
        log.info("Got a new order");
        log.debug("Order: {}", dto);
        var createOrder = new Order();
        createOrder.setId(UUID.randomUUID());
        var order = orderRepository.save(createOrder);
        var productIds = dto.getItems().stream().map(i -> i.getProduct()).toList();
        var products = productService.findByIds(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        var items = dto.getItems().stream()
                .map(i -> OrderItem.builder()
                        .id(UUID.randomUUID())
                        .product(products.get(i.getProduct()))
                        .order(order)
                        .quantity(i.getQuantity())
                        .entirePrice(i.getQuantity().multiply(products.get(i.getProduct()).getPrice()))
                        .build()
                )
                .toList();
        order.setItems(items);
        orderItemRepository.saveAll(items);
        var result = orderRepository.save(order);
        return mapper.fromEntity(result);
    }

    @Transactional
    public OrderDto getOrderById(UUID id) {
        log.info("Looking for order {}", id);
        var result = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return mapper.fromEntity(result);
    }


}