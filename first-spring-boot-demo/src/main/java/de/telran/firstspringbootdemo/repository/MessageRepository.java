package de.telran.firstspringbootdemo.repository;

import de.telran.firstspringbootdemo.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository  extends JpaRepository<Message, UUID> {
    Message save(Message message);
    void deleteById(UUID id);
    Optional<Message> findById(UUID id);
    Optional<Message> findByHeaderLike(String header);

//    @Query(value = "DELETE from messages m WHERE m.header = :header")
//    void deleteByHeader(@Param("header") String header);
}
