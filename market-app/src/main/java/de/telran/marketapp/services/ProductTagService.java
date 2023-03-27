package de.telran.marketapp.services;

import de.telran.marketapp.entities.ProductTag;
import de.telran.marketapp.repositiory.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProductTagService {

    TagRepository repository;

    public ProductTag findByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new IllegalArgumentException());
    }

    public List<ProductTag> findAllByNames(Collection<String> names) {
        return repository.findAllByNames(names);
    }
}
