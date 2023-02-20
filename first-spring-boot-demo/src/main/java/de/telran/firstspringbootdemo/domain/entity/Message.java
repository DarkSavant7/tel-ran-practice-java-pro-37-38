package de.telran.firstspringbootdemo.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.experimental.FieldDefaults;

import java.time.OffsetDateTime;
import java.util.UUID;

//@Entity
//@Table("some_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@With
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    UUID id;
    String header;
    String text;
    OffsetDateTime created;
    OffsetDateTime updated;
}
