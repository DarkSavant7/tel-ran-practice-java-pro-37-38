package de.telran.firstspringbootdemo.controller;

import de.telran.firstspringbootdemo.domain.dto.MessageDto;
import de.telran.firstspringbootdemo.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/messages")
@Tag(name = "Messages API", description = "Set of endpoints to work with messages")
public class MessageController {
    MessageService service;

    @Operation(summary = "Create message")
    @PostMapping("/")
    public MessageDto create(@RequestBody MessageDto message) {
        return service.create(message);
    }

    @Operation(summary = "Update message")
    @PutMapping("/")
    public MessageDto update(@RequestBody MessageDto message) {
        return service.update(message);
    }

    @Operation(summary = "Delete message")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @Operation(summary = "Find message by ID")
    @GetMapping("/{id}")
    public MessageDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Operation(summary = "Find message by heading")
    @GetMapping("/find")
    public MessageDto findByHeaderLike(@RequestParam("header") String header) {
        return service.findByHeaderLike(header);
    }
}
