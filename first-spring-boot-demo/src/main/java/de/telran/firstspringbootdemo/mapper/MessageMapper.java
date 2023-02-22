package de.telran.firstspringbootdemo.mapper;

import de.telran.firstspringbootdemo.domain.dto.MessageDto;
import de.telran.firstspringbootdemo.domain.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageDto fromEntity(Message message);

    Message toEntity(MessageDto message);
}
