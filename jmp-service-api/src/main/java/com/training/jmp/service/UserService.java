package com.training.jmp.service;

import com.training.jmp.service.dto.UserRequestDto;
import com.training.jmp.service.dto.UserResponseDto;

public interface UserService extends GenericService<UserRequestDto, UserResponseDto, Long> {
}