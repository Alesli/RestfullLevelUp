package com.training.jmp.service.controller;

import com.training.jmp.service.UserService;
import com.training.jmp.service.dto.UserRequestDto;
import com.training.jmp.service.request.UserRequestFailure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v2.0/level2/users")
@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        var user = userService.findById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @GetMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<?> getAllUsers() {
        var users = userService.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        var user = userService.deleteById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userDto) {
        var user = userService.save(userDto);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto userDto, @PathVariable("id") Long id) {
        if (userDto.getName() == null || userDto.getSurname() == null || userDto.getBirthday() == null) {
            return ResponseEntity.badRequest().body(new UserRequestFailure("Body is empty"));
        }
        var user = userService.update(userDto, id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}