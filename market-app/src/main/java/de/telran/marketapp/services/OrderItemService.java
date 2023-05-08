package de.telran.marketapp.services;

import de.telran.marketapp.dto.OrderItemFlatDto;
import de.telran.marketapp.dto.OrderItemFlatProjection;
import de.telran.marketapp.mapper.OrderMapper;
import de.telran.marketapp.repositiory.OrderItemCustomRepository;
import de.telran.marketapp.repositiory.OrderItemRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderItemService {

    OrderItemRepository orderItemRepository;
    OrderMapper mapper;
    OrderItemCustomRepository customRepository;

    @Transactional
    public Collection<OrderItemFlatProjection> findAll(BigDecimal price) {
        return orderItemRepository.findAllDtoAnotherWay(price);
    }

    @Transactional
    public Collection<OrderItemFlatDto> getCustomOrderItems(BigDecimal price) {
        return customRepository.getOrderItems(price);
    }


}
