package de.telran.firstspringbootdemo.repository;

import de.telran.firstspringbootdemo.domain.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

//@Repository
public interface MessageRepository /* extends JpaRepository<Message> */ {
    Message save(Message message);
    void deleteById(UUID id);
    Optional<Message> findById(UUID id);
    Optional<Message> findByHeaderLike(String header);
}
