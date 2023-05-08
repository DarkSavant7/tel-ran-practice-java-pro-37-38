package de.telran.marketapp.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderItemFlatProjection {
    void setId(UUID id);

    void setQuantity(BigDecimal quantity);

    void setEntirePrice(BigDecimal entirePrice);

    void setProductId(UUID productId);

    void setProductName(String productName);

    void setProductDescription(String productDescription);

    void setCurrentProductPrice(BigDecimal currentProductPrice);

    void setOrderId(UUID orderId);

    UUID getId();

    BigDecimal getQuantity();

    BigDecimal getEntirePrice();

    UUID getProductId();

    String getProductName();

    String getProductDescription();

    BigDecimal getCurrentProductPrice();

    UUID getOrderId();
}
