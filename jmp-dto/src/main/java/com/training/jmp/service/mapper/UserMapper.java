package com.training.jmp.service.mapper;

import com.training.jmp.service.dto.UserRequestDto;
import com.training.jmp.service.dto.UserResponseDto;
import com.training.jmp.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "subscriptions", ignore = true)
    User toEntity(UserRequestDto user);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    UserResponseDto toDto(User user);

    List<UserResponseDto> toDtoList(List<User> entityList);

}