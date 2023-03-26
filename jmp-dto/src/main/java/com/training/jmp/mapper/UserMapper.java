package com.training.jmp.mapper;

import com.training.jmp.dto.UserRequestDto;
import com.training.jmp.dto.UserResponseDto;
import com.training.jmp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "birthday", source = "birthday",
            dateFormat = "dd-MM-yyyy HH:mm:ss")
    User toEntity(UserRequestDto user);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "dd-MM-yyyy HH:mm:ss")
    UserResponseDto toDto(User user);

    List<User> toEntityList(List<UserRequestDto> dtoList);

    List<UserResponseDto> toDtoList(List<User> entityList);

}