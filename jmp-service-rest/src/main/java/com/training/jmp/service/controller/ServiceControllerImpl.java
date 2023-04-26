package com.training.jmp.service.controller;

import com.training.jmp.service.SubscriptionService;
import com.training.jmp.service.dto.SubscriptionRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2.0/level2/subscriptions")
@AllArgsConstructor
public class ServiceControllerImpl implements ServiceController {
    private final SubscriptionService subscriptionService;

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createSubscription(@RequestBody SubscriptionRequestDto subscriptionDto) {
        var subscription = subscriptionService.save(subscriptionDto);
        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getSubscription(@PathVariable("id") Long id) {
        var subscription = subscriptionService.findById(id);
        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllSubscription() {
        var subscriptions = subscriptionService.findAll();
        if (subscriptions != null && !subscriptions.isEmpty()) {
            return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        var subscription = subscriptionService.deleteById(id);
        if (subscription != null) {
            return new ResponseEntity<>(subscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateSubscription(@RequestBody SubscriptionRequestDto subscriptionDto, @PathVariable("id") Long id) {
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