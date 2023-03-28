package com.training.jmp.service.rest.controller;

import com.training.jmp.dto.SubscriptionRequestDto;
import com.training.jmp.dto.SubscriptionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "subscriptions")
public interface ServiceController {
    @ApiOperation(response = SubscriptionRequestDto.class, value = "Add new Subscription", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Subscription created", response = SubscriptionRequestDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found")})
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscription);

    @ApiOperation(response = SubscriptionResponseDto.class, value = "Find Subscription by Id", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found")})
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("id") Long id);

    @ApiOperation(value = "Delete based on primary key", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found")})
    public ResponseEntity delete(@PathVariable("id") Long id);

    @ApiOperation(response = List.class, value = "Find all data", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found")})
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription();

    @ApiOperation(response = SubscriptionResponseDto.class, value = "Update Subscription", produces = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subscription details retrieved", response = SubscriptionResponseDto.class),
            @ApiResponse(code = 404, message = "Subscription data not found")})
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto dto, @PathVariable("id") Long id);
}