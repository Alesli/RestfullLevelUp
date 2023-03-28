package com.training.jmp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private UserRequestDto userRequestDto;
    private UserAction action;

}
