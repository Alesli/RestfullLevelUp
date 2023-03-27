package com.training.jmp.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<Q, R, M> {
    R save(Q entity);

    void deleteById(M id);

    R findById(M id);

    List<R> findAll();

    R update(Q entity, M id);
}