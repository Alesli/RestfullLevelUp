package com.training.jmp.service;

import com.training.jmp.service.dto.SubscriptionRequestDto;
import com.training.jmp.service.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService extends GenericService<SubscriptionRequestDto, SubscriptionResponseDto, Long> {

    List<SubscriptionResponseDto> findByUserId(Long userId);
}