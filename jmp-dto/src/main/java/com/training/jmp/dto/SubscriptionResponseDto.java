package com.training.jmp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionResponseDto {

    private Long id;
    private Long userId;
    private String startDate;

    @JsonCreator
    public SubscriptionResponseDto() {
    }

    @JsonCreator
    public SubscriptionResponseDto(@JsonProperty("id") Long id,
                 @JsonProperty("userId") Long userId,
                 @JsonProperty("startDate") String startDate) {
        this.id = id;
        this.userId = userId;
        this.startDate = startDate;
    }
}
