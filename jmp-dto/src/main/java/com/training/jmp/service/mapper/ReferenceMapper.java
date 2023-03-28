package com.training.jmp.service.mapper;

import com.training.jmp.service.repo.UserRepository;
import com.training.jmp.service.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ReferenceMapper {
    private final UserRepository repository;

    public User toUser(Long id) {
        if (id != null) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    public Long toUserId(User input) {
        return Optional.ofNullable(input)
                .map(User::getId)
                .orElse(null);
    }

}