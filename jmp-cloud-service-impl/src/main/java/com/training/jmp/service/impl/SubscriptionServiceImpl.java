package com.training.jmp.service.impl;

import com.training.jmp.repo.SubscriptionRepository;
import com.training.jmp.entity.Subscription;
import com.training.jmp.service.SubscriptionService;
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

    @Override
    public Subscription save(Subscription entity) {
        return repository.save(entity);
    }

    @Override
    public List<Subscription> save(List<Subscription> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Subscription> findAll() {
        return repository.findAll();
    }

    @Override
    public Subscription update(Subscription entity, Long id) {
        Optional<Subscription> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}
