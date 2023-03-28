package com.training.jmp.service.rest.controller.impl;

import com.training.jmp.dto.UserAction;
import com.training.jmp.dto.UserRequest;
import com.training.jmp.dto.UserRequestDto;
import com.training.jmp.dto.UserResponseDto;
import com.training.jmp.service.UserRequestFailure;
import com.training.jmp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/level0")
@AllArgsConstructor
@Api(value = "users")
public class UserControllerL0 {

    private final UserService userService;

    @PostMapping(produces = "application/json")
    @ApiOperation(response = UserResponseDto.class, value = "Add new user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User created", response = UserRequestDto.class),
            @ApiResponse(code = 404, message = "User data not found")})
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        UserRequestDto userDto = userRequest.getUserRequestDto();
        if (userDto == null) {
            return ResponseEntity.ok().body(new UserRequestFailure("Body is empty"));
        }
        UserAction action = userRequest.getAction();
        switch (action) {
            case CREATE:
                UserResponseDto createUser = userService.save(userDto);
                return ResponseEntity.ok(createUser);
            case DELETE:
                userService.deleteById(userDto.getId());
                return ResponseEntity.ok("Deleted");
            case GET:
                UserResponseDto getUser = userService.findById(userDto.getId());
                if (getUser == null) {
                    return ResponseEntity.ok().body(new UserRequestFailure("User was not found"));
                }
                return ResponseEntity.ok(getUser);
            default:
                return ResponseEntity.ok().body(new UserRequestFailure("Body is empty"));

        }
    }
}
