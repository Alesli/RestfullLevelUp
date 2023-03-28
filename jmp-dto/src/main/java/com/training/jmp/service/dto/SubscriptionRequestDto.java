package com.training.jmp.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionRequestDto {

    private Long id;
    private Long userId;
}
