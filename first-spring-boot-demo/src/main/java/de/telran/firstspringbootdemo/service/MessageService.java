package de.telran.firstspringbootdemo.service;

import de.telran.firstspringbootdemo.domain.dto.MessageDto;

import java.util.UUID;

public interface MessageService {
    MessageDto create(MessageDto message);
    MessageDto update(MessageDto message);
    void delete(UUID id);
    MessageDto findById(UUID id);
    MessageDto findByHeaderLike(String header);
}
