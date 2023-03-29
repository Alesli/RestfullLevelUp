package com.training.jmp.service.request;

import com.training.jmp.service.dto.UserRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private UserRequestDto user;
    private UserAction action;

}
