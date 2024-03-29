package de.telran.marketapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Table(name = "product_tags")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String name;
    String description;
    @ManyToMany
    @JoinTable(name = "products_product_tags",
            joinColumns = {@JoinColumn(name = "product_tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    Set<Product> products;
    @CreationTimestamp
    @Column(name = "created", updatable = false)
    OffsetDateTime created;
    @UpdateTimestamp
    OffsetDateTime updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTag user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
