package de.telran.marketapp.repositiory;

import de.telran.marketapp.entities.Role;
import de.telran.marketapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(@Param("name") String name);
}
