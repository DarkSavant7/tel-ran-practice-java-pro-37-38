package de.telran.marketapp.services;

import de.telran.marketapp.dto.UserRegisterDto;
import de.telran.marketapp.entities.Role;
import de.telran.marketapp.entities.User;
import de.telran.marketapp.repositiory.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {
    private static final String CUSTOMER_ROLE_NAME = "CUSTOMER";
    @NonFinal
    Role customerRole;
    UserRepository repository;
    BCryptPasswordEncoder passwordEncoder;
    RoleService roleService;

    @PostConstruct
    public void init() {
        this.customerRole = roleService.getRoleByName(CUSTOMER_ROLE_NAME);
    }

    @Transactional
    public User register(UserRegisterDto dto) {
        if (repository.existsByLogin(dto.getLogin())) {
            throw new IllegalArgumentException("User with this login already exists");
        }
        var user = new User();
        user.setRoles(List.of(customerRole));
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return repository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Transactional
    public User getByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.getRoles().stream().findAny();
        return user;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
    }
}
