package com.training.jmp.service.rest.controller.impl;

import com.training.jmp.dto.SubscriptionRequestDto;
import com.training.jmp.dto.SubscriptionResponseDto;
import com.training.jmp.mapper.SubscriptionMapper;
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
@RequestMapping("/subscriptions")
@AllArgsConstructor
public class ServiceControllerImpl implements ServiceController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@RequestBody SubscriptionRequestDto subscriptionDto) {
        var subscription = subscriptionMapper.toEntity(subscriptionDto);
        var subscrEntity = subscriptionMapper.toDto(subscriptionService.save(subscription));
        if (subscrEntity != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subscrEntity.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable("id") Long id) {
        var subscription = subscriptionService.findById(id).orElse(null);
        var subscrEntity = subscriptionMapper.toDto(subscription);
        if (subscrEntity != null) {
            return new ResponseEntity<>(subscrEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> getAllSubscription() {
        var subscriptions = subscriptionMapper.toDtoList(subscriptionService.findAll());
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
        var subscription = subscriptionMapper.toEntity(subscriptionDto);
        var subscrEntity = subscriptionMapper.toDto(subscriptionService.update(subscription, id));
        if (subscrEntity != null) {
            return new ResponseEntity<>(subscrEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}