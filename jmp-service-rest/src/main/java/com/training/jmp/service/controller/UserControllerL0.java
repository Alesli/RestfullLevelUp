package com.training.jmp.service.controller;

import com.training.jmp.service.dto.UserAction;
import com.training.jmp.service.dto.UserRequestDto;
import com.training.jmp.service.dto.UserResponseDto;
import com.training.jmp.service.UserRequestFailure;
import com.training.jmp.service.UserService;
import com.training.jmp.service.request.UserRequest;
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
        var user = userRequest.getUser();
        if (user == null) {
            return ResponseEntity.ok().body(new UserRequestFailure("Body is empty"));
        }
        var action = userRequest.getAction();
        switch (action) {
            case CREATE:
                var createUser = userService.save(user);
                return ResponseEntity.ok(createUser);
            case DELETE:
                var delUser = userService.deleteById(user.getId());
                if (delUser == null) {
                    return ResponseEntity.ok().body(new UserRequestFailure("User was not found"));
                }
                return ResponseEntity.ok(delUser);
            case GET:
                var getUser = userService.findById(user.getId());
                if (getUser == null) {
                    return ResponseEntity.ok().body(new UserRequestFailure("User was not found"));
                }
                return ResponseEntity.ok(getUser);
            default:
                return ResponseEntity.ok().body(new UserRequestFailure("Body is empty"));

        }
    }
}
