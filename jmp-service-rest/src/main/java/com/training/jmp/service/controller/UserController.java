package com.training.jmp.service.controller;

import com.training.jmp.service.dto.UserRequestDto;
import com.training.jmp.service.dto.UserResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "users")
public interface UserController {

    @ApiOperation(response = UserResponseDto.class, value = "Add new user", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User created", response = UserRequestDto.class),
            @ApiResponse(code = 404, message = "User data not found")})
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user);

    @ApiOperation(response = UserResponseDto.class, value = "Find user by Id", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User details retrieved", response = UserRequestDto.class),
            @ApiResponse(code = 404, message = "User data not found")})
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete based on primary key", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User details retrieved", response = UserRequestDto.class),
            @ApiResponse(code = 404, message = "User data not found")})
    public ResponseEntity deleteUser(@PathVariable("id") Long id);

    @ApiOperation(response = List.class, value = "Find all data", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User details retrieved", response = UserRequestDto.class),
            @ApiResponse(code = 404, message = "User data not found")})
    public ResponseEntity<List<UserResponseDto>> getAllUser();

    @ApiOperation(response = UserResponseDto.class, value = "Update user", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User details retrieved", response = UserRequestDto.class),
            @ApiResponse(code = 404, message = "User data not found")})
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto dto, @PathVariable("id") Long id);
}
