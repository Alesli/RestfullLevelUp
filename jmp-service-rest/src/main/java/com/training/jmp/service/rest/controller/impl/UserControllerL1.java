package com.training.jmp.service.rest.controller.impl;

import com.training.jmp.dto.UserRequestDto;
import com.training.jmp.dto.UserResponseDto;
import com.training.jmp.entity.User;
import com.training.jmp.mapper.UserMapper;
import com.training.jmp.service.UserService;
import com.training.jmp.service.rest.controller.UserController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/l1/users")
@RestController
@AllArgsConstructor
public class UserControllerL1 implements UserController {
    private final UserService userService;

    @Override
    public ResponseEntity<UserResponseDto> createUser(UserRequestDto user) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> getUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity deleteUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getAllUser() {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> updateUser(UserRequestDto dto, Long id) {
        return null;
    }
}