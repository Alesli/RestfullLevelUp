package com.training.jmp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionRequestDto {

    private Long id;
    private Long userId;
}