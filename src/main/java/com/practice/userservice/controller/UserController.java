package com.practice.userservice.controller;

import com.practice.userservice.entities.User;
import com.practice.userservice.exceptions.UserNotFoundException;
import com.practice.userservice.models.UserDto;
import com.practice.userservice.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@NotNull @PathVariable("id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") @NotNull Long id,
                                           @RequestBody @Validated UserDto userDto
    ) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUserById(id, userDto));
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUserDetails(
            @RequestParam(name = "name", defaultValue = "", required = false) String name,
            @RequestParam(name = "userid", defaultValue = "", required = false) String userId,
            @RequestParam(name = "gender", defaultValue = "", required = false) String gender,
            @RequestParam(name = "min", defaultValue = "0", required = false) Integer minAge,
            @RequestParam(name = "max", defaultValue = "0", required = false) Integer maxAge,
            @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
        return ResponseEntity.ok(userService.getPageList(name, userId, gender, minAge, maxAge, pageNum, pageSize));
    }
}
