package de.telran.marketapp.services;

import de.telran.marketapp.dto.AnotherOrderDto;
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
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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
        var order = orderRepository.save(createOrder);
        var productIds = dto.getItems().stream().map(i -> i.getProduct()).toList();
        var products = productService.findByIds(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        var items = dto.getItems().stream()
                .map(i -> OrderItem.builder()
                        .product(products.get(i.getProduct()))
                        .order(order)
                        .quantity(i.getQuantity())
                        .entirePrice(i.getQuantity().multiply(products.get(i.getProduct()).getPrice()))
                        .build()
                )
                .toList();
        items = orderItemRepository.saveAll(items);
        order.setItems(items);
        var price = items.stream()
                .map(OrderItem::getEntirePrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        order.setPrice(price);
        log.info("Order: {}", order);
        var result = orderRepository.save(order);
        return mapper.fromEntity(result);
    }

    @Transactional
    public OrderDto getOrderById(UUID id) {
        log.info("Looking for order {}", id);
        var result = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        return mapper.fromEntity(result);
    }

    @Transactional
    public Collection<AnotherOrderDto> findAll() {
        var items = orderItemRepository.findAllDto();
        var orders = orderRepository.findAllDto().stream()
                .collect(Collectors.toMap(AnotherOrderDto::getId, Function.identity()));
        orders.values().forEach(o -> o.setItems(new ArrayList<>()));
        items.forEach(i -> orders.get(i.getOrderId()).getItems().add(i));
        return orders.values();
    }


}
