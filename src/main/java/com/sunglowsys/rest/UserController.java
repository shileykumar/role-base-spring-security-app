package com.sunglowsys.rest;

import com.sunglowsys.domain.User;
import com.sunglowsys.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/users")
    public ResponseEntity<List<User>> getUser() {
       List<User> users = userService.findAll();
       return ResponseEntity
               .ok()
               .body(users);
    }

    @PostMapping("/create/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        User result = userService.save(user);
        return ResponseEntity
                .created(new URI("/create/users" + result.getId()))
                .body(result);
    }
}
