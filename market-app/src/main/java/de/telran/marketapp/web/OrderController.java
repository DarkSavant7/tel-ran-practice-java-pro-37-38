package de.telran.marketapp.web;

import de.telran.marketapp.dto.AnotherOrderDto;
import de.telran.marketapp.dto.CreateOrderDto;
import de.telran.marketapp.dto.OrderDto;
import de.telran.marketapp.dto.OrderItemFlatDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.services.OrderService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderController {

    OrderService service;

    @PostMapping
    public OrderDto create(@RequestBody CreateOrderDto order) {
        return service.createOrder(order);
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable(value = "id", required = false) UUID id) {
        return service.getOrderById(id);
    }

    @GetMapping
    public Collection<AnotherOrderDto> findAll() {
        return service.findAll();
    }
}
