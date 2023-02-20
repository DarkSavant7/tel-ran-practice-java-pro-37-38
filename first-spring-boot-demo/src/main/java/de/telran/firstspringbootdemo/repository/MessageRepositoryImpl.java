package de.telran.firstspringbootdemo.repository;

import de.telran.firstspringbootdemo.domain.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class MessageRepositoryImpl implements MessageRepository {
    @Override
    public Message save(Message message) {
        log.info("Got a message to save");
        if (message.getId() == null) {
            message = message.withId(UUID.randomUUID());
        }
        log.info("Message {} saved", message.getId());
        return message;
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting message {}", id);
        log.info("Message {} deleted", id);
    }

    @Override
    public Optional<Message> findById(UUID id) {
        log.info("Finding message {}", id);
        if (Math.random() * 100 > 80) {
            return Optional.empty();
        }
        var message = Message.builder()
                .id(UUID.randomUUID())
                .header("Header " + id)
                .text("Text " + id)
                .build();
        log.info("Message {} found", id);
        return Optional.of(message);
    }

    @Override
    public Optional<Message> findByHeaderLike(String header) {
        log.info("Finding message with header like {}", header);
        var message = Message.builder()
                .id(UUID.randomUUID())
                .header("Header " + header)
                .text("Text ")
                .build();
        log.info("Message found");
        return Optional.of(message);
    }
}
