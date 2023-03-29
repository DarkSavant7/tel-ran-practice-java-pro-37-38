package de.telran.marketapp.mapper;

import de.telran.marketapp.dto.CreateProductDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.entities.ProductTag;
import de.telran.marketapp.services.ProductTagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductMapper {
    ProductTagService productTagService;

    public Product mapCreateDtoToProduct(CreateProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
//                .tags(dto.getTags().stream().map(t -> productTagService.findByName(t)).toList())
                .tags(new HashSet<>(productTagService.findAllByNames(dto.getTags())))
                .build();
    }

    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
//                .tags(product.getTags().stream().map(ProductTag::getName).toList())
                .build();
    }
}
