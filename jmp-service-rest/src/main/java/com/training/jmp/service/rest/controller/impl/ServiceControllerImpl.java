package com.training.jmp.service.rest.controller.impl;

import com.training.jmp.dto.SubscriptionRequestDto;
import com.training.jmp.dto.SubscriptionResponseDto;
import com.training.jmp.service.SubscriptionService;
import com.training.jmp.service.rest.controller.ServiceController;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/level2/subscriptions")
@AllArgsConstructor
public class ServiceControllerImpl implements ServiceController {
    private final SubscriptionService subscriptionService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionDto) {
        var subscription = subscriptionService.save(subscriptionDto);
        if (subscription != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subscription.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("id") Long id) {
        var subscription = subscriptionService.findById(id);
        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        var subscriptions = subscriptionService.findAll();
        if (subscriptions != null && !subscriptions.isEmpty()) {
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        subscriptionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionDto, @PathVariable("id") Long id) {
        if (subscriptionDto.getUserId() == null) {
            return ResponseEntity.badRequest().build();
        }
        var subscription = subscriptionService.update(subscriptionDto, id);
        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}