package de.telran.marketapp.mapper;

import de.telran.marketapp.dto.CreateProductDto;
import de.telran.marketapp.dto.OrderDto;
import de.telran.marketapp.dto.OrderItemDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.entities.Order;
import de.telran.marketapp.entities.OrderItem;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.entities.ProductTag;
import de.telran.marketapp.services.ProductService;
import de.telran.marketapp.services.ProductTagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderMapper {
    ProductMapper productMapper;
    ProductService productService;
    public OrderItemDto itemFromEntity(OrderItem item) {
        return OrderItemDto.builder()
                .id(item.getId())
                .entirePrice(item.getEntirePrice())
                .quantity(item.getQuantity())
                .product(productMapper.mapToDto(item.getProduct())) //@TODO think about optimization, maybe projection
                .build();
    }

    public OrderItem itemToEntity(OrderItemDto dto) {
        return OrderItem.builder()
                .product(productService.getById(dto.getProduct().getId())) //@TODO optimize
//                .order(order)
                .id(dto.getId())
                .entirePrice(dto.getEntirePrice())
                .quantity(dto.getQuantity())
                .build();
    }

    public Order toEntity(OrderDto dto) {
        return Order.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .items(dto.getItems().stream().map(this::itemToEntity).toList())
                .build();
    }

    public OrderDto fromEntity(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .created(order.getCreated())
                .updated(order.getUpdated())
                .price(order.getPrice())
                .items(order.getItems().stream().map(this::itemFromEntity).toList())
                .build();
    }
}
