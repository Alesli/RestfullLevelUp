package com.training.jmp.service.impl;

import com.training.jmp.dto.UserRequestDto;
import com.training.jmp.dto.UserResponseDto;
import com.training.jmp.entity.User;
import com.training.jmp.mapper.UserMapper;
import com.training.jmp.repo.UserRepository;
import com.training.jmp.service.UserService;
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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        var user =  repository.findById(id).orElse(null);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userMapper.toDtoList(repository.findAll());
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
