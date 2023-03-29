package de.telran.marketapp.mapper;

import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.dto.ProductTagDto;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.entities.ProductTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapstructMapper {

    ProductDto mapToDto(Product product);

    @Mappings({
            @Mapping(source = "name", target = "tag")
    })
    ProductTagDto tagToDto(ProductTag productTag);
}
