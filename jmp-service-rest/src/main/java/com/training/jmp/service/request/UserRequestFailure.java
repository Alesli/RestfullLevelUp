package com.training.jmp.service.request;

public class UserRequestFailure extends RuntimeException{
    private static final long serialVersionUID = -3891534644498426670L;

    public UserRequestFailure(String message) {
        super(message);
    }
}
