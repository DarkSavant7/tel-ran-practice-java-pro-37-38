package de.telran.marketapp.web;

import de.telran.marketapp.dto.CreateProductDto;
import de.telran.marketapp.dto.ProductDto;
import de.telran.marketapp.services.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductController {

    ProductService service;

    @PostMapping
//    @RequestMapping(method = RequestMethod.POST)
    public ProductDto create(@RequestBody CreateProductDto product) {
        return service.create(product);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable(value = "id", required = false) UUID id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {
        size = size == null ? 20 : size;
        page = page == null ? 0 : page;
        return service.findAll(page, size);
    }

    @DeleteMapping
    public void deleteById(@RequestParam("id") UUID id) {
        service.deleteById(id);
    }
}
