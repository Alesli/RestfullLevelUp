package com.training.jmp.service.controller;

import com.training.jmp.service.UserService;
import com.training.jmp.service.dto.UserResponseDto;
import com.training.jmp.service.request.UserRequest;
import com.training.jmp.service.request.UserRequestFailure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1.0/level1")
@RestController
@AllArgsConstructor
@Api(value = "users")
public class UserControllerL1 {
    private final UserService userService;

    @PostMapping(path = "/users", produces = "application/json")
    @ApiOperation(
            response = UserResponseDto.class,
            produces = "application/json",
            value = "Returns list of all Users.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users details retrieved", response = List.class),
            @ApiResponse(code = 200, message = "Users not found", response = UserRequestFailure.class),
            @ApiResponse(code = 200, message = "Operation is not supported", response = UserRequestFailure.class),
    })
    public @ResponseBody ResponseEntity<?> getAllUsers(@RequestBody UserRequest userRequest) {
        var users = userService.findAll();
        if (CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(new UserRequestFailure("The list of users is empty"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/users/{id}",
            produces = "application/json")
    @ApiOperation(
            response = UserResponseDto.class,
            value = "Get, delete or update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users details retrieved", response = List.class),
            @ApiResponse(code = 200, message = "User data not found", response = UserRequestFailure.class),
    })
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        var action = userRequest.getAction();
        var user = userRequest.getUser();
        switch (action) {
            case UPDATE:
                var updateUser = userService.update(user, id);
                return ResponseEntity.ok(updateUser);
            case DELETE:
                var delUser = userService.deleteById(id);
                if (delUser == null) {
                    return ResponseEntity.ok().body(new UserRequestFailure("User was not found"));
                }
                return ResponseEntity.ok(delUser);
            case CREATE:
                var createUser = userService.save(user);
                return ResponseEntity.ok(createUser);
            case GET:
                var getUser = userService.findById(id);
                if (getUser == null) {
                    return ResponseEntity.ok().body(new UserRequestFailure("User was not found"));
                }
                return ResponseEntity.ok(getUser);
            default:
                return ResponseEntity.ok().body(new UserRequestFailure("Operation is not supported"));
        }
    }
}