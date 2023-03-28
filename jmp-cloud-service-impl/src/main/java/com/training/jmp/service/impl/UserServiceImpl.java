package com.training.jmp.service.impl;

import com.training.jmp.service.UserService;
import com.training.jmp.service.dto.UserRequestDto;
import com.training.jmp.service.dto.UserResponseDto;
import com.training.jmp.service.entity.User;
import com.training.jmp.service.mapper.UserMapper;
import com.training.jmp.service.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto save(UserRequestDto userDto) {
        var user = userMapper.toEntity(userDto);
        return userMapper.toDto(repository.save(user));
    }

    @Override
    public UserResponseDto deleteById(Long id) {
        var userOptional = repository.findById(id);
        UserResponseDto user = null;
        if (userOptional.isPresent()) {
            user = userMapper.toDto(userOptional.get());
            repository.deleteById(id);
        }
        return user;
    }

    @Override
    public UserResponseDto findById(Long id) {
        var user = repository.findById(id).orElse(null);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userMapper.toDtoList((List<User>) repository.findAll());
    }

    @Override
    public UserResponseDto update(UserRequestDto userDto, Long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isPresent()) {
            var user = userMapper.toEntity(userDto);
            return userMapper.toDto(repository.save(user));
        }
        return null;
    }
}
