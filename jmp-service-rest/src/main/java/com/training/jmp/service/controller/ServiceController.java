package com.training.jmp.service.controller;

import com.training.jmp.service.dto.SubscriptionRequestDto;
import com.training.jmp.service.dto.SubscriptionResponseDto;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "subscriptions")
public interface ServiceController {
    @ApiOperation(response = SubscriptionRequestDto.class, value = "Add new Subscription", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Subscription created", response = SubscriptionRequestDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<?> createSubscription(@RequestBody SubscriptionRequestDto subscription);

    @ApiOperation(response = SubscriptionResponseDto.class, value = "Find Subscription by Id", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<?> getSubscription(@ApiParam("Id of the Subscription to be obtained. Cannot be empty.") @PathVariable("id") Long id);

    @ApiOperation(value = "Delete based on primary key", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<?> delete(@ApiParam("Id of the Subscription to be obtained. Cannot be empty.") @PathVariable("id") Long id);

    @ApiOperation(response = List.class, value = "Find all data", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<?> getAllSubscription();

    @ApiOperation(response = SubscriptionResponseDto.class, value = "Update Subscription", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    ResponseEntity<?> updateSubscription(@RequestBody SubscriptionRequestDto dto, @ApiParam("Id of the Subscription to be obtained. Cannot be empty.") @PathVariable("id") Long id);
}