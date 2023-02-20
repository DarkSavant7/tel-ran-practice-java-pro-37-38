package de.telran.firstspringbootdemo.service;

import de.telran.firstspringbootdemo.domain.dto.MessageDto;
import de.telran.firstspringbootdemo.mapper.MessageMapper;
import de.telran.firstspringbootdemo.repository.MessageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;
    MessageMapper mapper;

    @Override
    public MessageDto create(MessageDto message) {
        log.info("Creating message");
        var entity = mapper.toEntity(message);
        entity = messageRepository.save(entity);
        return mapper.fromEntity(entity);
    }

    @Override
    public MessageDto update(MessageDto message) {
        log.info("Updating message");
        var entity = mapper.toEntity(message);
        entity = messageRepository.save(entity);
        return mapper.fromEntity(entity);
    }

    @Override
    public void delete(UUID id) {
        log.info("Deleting message {}", id);
        messageRepository.deleteById(id);
    }

    @Override
    public MessageDto findById(UUID id) {
        log.info("Finding message {}", id);
        var entity = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id not found"));
        log.info(" {} ", entity);
       return mapper.fromEntity(entity);
    }

    @Override
    public MessageDto findByHeaderLike(String header) {
        log.info("Finding message by header {}", header);
        var entity = messageRepository.findByHeaderLike(header)
                .orElseThrow(() -> new IllegalArgumentException("Header not found"));
        return mapper.fromEntity(entity);
    }
}
