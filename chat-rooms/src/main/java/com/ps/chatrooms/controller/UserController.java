package com.ps.chatrooms.controller;

import com.ps.chatrooms.model.dto.UserRegisterDto;
import com.ps.chatrooms.model.entity.User;
import com.ps.chatrooms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public User registerUser(@Valid @RequestBody UserRegisterDto dto){
        return this.userService.registerUser(dto.getUsername(),
                dto.getEmail(), dto.getPassword());
    }

    @GetMapping
    public List<User> loadAll() {
        return this.userService.findAll();
    }



    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return "user with id " + id + " deleted";
    }

    @GetMapping("/check/{username}")
    public Map checkUsername(@PathVariable String username){
        User user = this.userService.findByUsername(username);
        return Collections.singletonMap("exists", Objects.nonNull(user));
    }
}
