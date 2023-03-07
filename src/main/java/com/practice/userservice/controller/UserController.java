package com.practice.userservice.controller;

import com.practice.userservice.entities.User;
import com.practice.userservice.exceptions.EntityNotFoundException;
import com.practice.userservice.models.UserDto;
import com.practice.userservice.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@ComponentScan
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto){
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") @NotNull Long id,
                                              @RequestBody @Validated UserDto userDto
        ) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<User>> getUserPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(name = "userid", required = false) String userId,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "min",  required = false) Integer minAge,
            @RequestParam(name = "max",  required = false) Integer maxAge,
            @RequestParam(name = "office",  required = false) String office,
            Pageable pageable) {
        return ResponseEntity.ok(userService.getPageList(name, userId, gender, minAge, maxAge,office,pageable));
    }
}
