package de.telran.hibernate.h2;

import java.util.List;

public interface UserDao {
    User findById(Long id);
    User findByName(String name);
    UserDto findDtoById(Long id);
    List<User> findAll();
    void save(User user);
    void updateNameById(Long id, String newName);
    void testCaching();
}
