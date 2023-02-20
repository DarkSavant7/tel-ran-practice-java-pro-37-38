package de.telran.firstspringbootdemo.mapper;

import de.telran.firstspringbootdemo.domain.dto.MessageDto;
import de.telran.firstspringbootdemo.domain.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    public abstract MessageDto fromEntity(Message message);
    public abstract Message toEntity(MessageDto message);
}
