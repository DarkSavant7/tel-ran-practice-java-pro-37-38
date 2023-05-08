package de.telran.marketapp.mapper;

import de.telran.marketapp.dto.OrderItemFlatDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.dto.ProductTagDto;
import de.telran.marketapp.entities.OrderItem;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.entities.ProductTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapstructMapper {

    ProductDto mapToDto(Product product);

    //https://www.baeldung.com/spring-expression-language
    //https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/expressions.html
    @Mappings({
            @Mapping(source = "name", target = "tag"),
//            @Mapping(target = "description", expression = ("java(getProducts().get(0).getDescription()"))
    })
    ProductTagDto tagToDto(ProductTag productTag);

//    @Mappings({
//            @Mapping(target = "productId", expression = ("java(getProduct().getId())")),
//            @Mapping(target = "productName", expression = ("java(getProduct().getName())"))
//    })
//    OrderItemFlatDto orderItemToFlat(OrderItem orderItem);
}
