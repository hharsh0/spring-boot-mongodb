package org.example.mongodb.controllers;

import org.example.mongodb.models.User;
import org.example.mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User saveUser = userService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable String userId){
        if(userService.isExist(userId)){
            userService.deleteUser(userId);
            return "User with user id: "+userId+" is deleted";
        }else{
            return "User with user id"+userId+" is not found";
        }
    }

}
