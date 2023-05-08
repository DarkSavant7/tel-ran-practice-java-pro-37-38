package de.telran.marketapp.web;

import de.telran.marketapp.dto.AnotherOrderDto;
import de.telran.marketapp.dto.CreateOrderDto;
import de.telran.marketapp.dto.OrderDto;
import de.telran.marketapp.dto.OrderItemFlatDto;
import de.telran.marketapp.dto.OrderItemFlatProjection;
import de.telran.marketapp.services.OrderItemService;
import de.telran.marketapp.services.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/orders/items")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderItemController {

    OrderItemService service;

    @GetMapping("/{price}")
    public Collection<OrderItemFlatProjection> findAll(@PathVariable BigDecimal price) {
        return service.findAll(price);
    }

    @GetMapping()// url/path?price=10.12&another=mkdfdvmnokvfd
    public Collection<OrderItemFlatDto> findCustomOrderItems(@RequestParam("price") BigDecimal price) {
        return service.getCustomOrderItems(price);
    }
}
