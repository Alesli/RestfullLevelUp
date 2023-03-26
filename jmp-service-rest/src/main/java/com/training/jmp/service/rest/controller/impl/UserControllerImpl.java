package com.training.jmp.service.rest.controller.impl;

import com.training.jmp.dto.UserRequestDto;
import com.training.jmp.dto.UserResponseDto;
import com.training.jmp.mapper.UserMapper;
import com.training.jmp.service.UserService;
import com.training.jmp.service.rest.controller.UserController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDTO) {
        var user = userMapper.toEntity(userDTO);
        var userEntity = userMapper.toDto(userService.save(user));
        if (userEntity != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userEntity.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id) {
        var user = userService.findById(id).orElse(null);
        var userEntity = userMapper.toDto(user);
        if (userEntity != null) {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping
    public @ResponseBody ResponseEntity<List<UserResponseDto>> getAllUser() {
        var users = userMapper.toDtoList(userService.findAll());
        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userDto, @PathVariable("id") Long id) {
        if (userDto.getName() == null || userDto.getSurname() == null || userDto.getBirthday() == null) {
            return ResponseEntity.badRequest().build();
        }
        var user = userMapper.toEntity(userDto);
        var userEntity = userMapper.toDto(userService.update(user, id));
        if (userEntity != null) {
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}