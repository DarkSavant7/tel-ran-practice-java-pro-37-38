package de.telran.firstspringbootdemo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "DTO for message")
public class MessageDto {
    @Schema(name = "id", description = "Message id")
    UUID id;
    @Schema(name = "header", description = "Message header")
    String header;
    @Schema(name = "text", description = "Message text")
    String text;
}
