package com.training.jmp.service.impl;

import com.training.jmp.service.SubscriptionService;
import com.training.jmp.service.dto.SubscriptionRequestDto;
import com.training.jmp.service.dto.SubscriptionResponseDto;
import com.training.jmp.service.entity.Subscription;
import com.training.jmp.service.mapper.SubscriptionMapper;
import com.training.jmp.service.repo.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponseDto save(SubscriptionRequestDto subscriptionDto) {
        var subscription = subscriptionMapper.toEntity(subscriptionDto);
        return subscriptionMapper.toDto(repository.save(subscription));
    }

    @Override
    public SubscriptionResponseDto deleteById(Long id) {
        var subscrOptional = repository.findById(id);
        SubscriptionResponseDto subscription = null;
        if (subscrOptional.isPresent()) {
            subscription = subscriptionMapper.toDto(subscrOptional.get());
            repository.deleteById(id);
        }
        return subscription;
    }

    @Override
    public SubscriptionResponseDto findById(Long id) {
        var subscription = repository.findById(id).orElse(null);
        return subscriptionMapper.toDto(subscription);
    }

    @Override
    public List<SubscriptionResponseDto> findAll() {
        return subscriptionMapper.toDtoList((List<Subscription>) repository.findAll());
    }

    @Override
    public SubscriptionResponseDto update(SubscriptionRequestDto subscriptionDto, Long id) {
        Optional<Subscription> optional = repository.findById(id);
        if (optional.isPresent()) {
            var subscription = subscriptionMapper.toEntity(subscriptionDto);
            return subscriptionMapper.toDto(repository.save(subscription));
        }
        return null;
    }
}
