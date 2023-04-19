package de.telran.marketapp.services;

import de.telran.marketapp.client.DemoClient;
import de.telran.marketapp.dto.AnotherOrderDto;
import de.telran.marketapp.dto.CreateOrderDto;
import de.telran.marketapp.dto.OrderDto;
import de.telran.marketapp.dto.client.FakeClientDto;
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
public class FakeClientService {

    DemoClient demoClient;

    public FakeClientDto getFakeClientDtoById(UUID id) {
        //...... some logic
        return demoClient.getFakeDtoById(id);
    }
}
