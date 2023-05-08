package de.telran.marketapp.services;

import de.telran.marketapp.entities.Role;
import de.telran.marketapp.repositiory.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleService {
    RoleRepository repository;

    public Role getRoleByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new NotFoundException("Role not found"));
    }
}
