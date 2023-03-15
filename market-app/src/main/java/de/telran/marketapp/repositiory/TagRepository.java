package de.telran.marketapp.repositiory;

import de.telran.marketapp.entities.Product;
import de.telran.marketapp.entities.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<ProductTag, UUID> {

//    @Query(value = "", nativeQuery = true)
    Optional<ProductTag> findByName(@Param("name") String name);

    @Query("SELECT p FROM ProductTag p WHERE p.name IN :names")
    List<ProductTag> findAllByNames(@Param("names") Collection<String> names);
}
