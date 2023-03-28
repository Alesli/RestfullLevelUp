package com.training.jmp.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponseDto {

    private Long id;
    private Long userId;
    private String startDate;

}
