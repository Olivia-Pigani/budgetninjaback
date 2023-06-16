package com.budgetninja.back.controller;

import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("")
    public List<UserModel> getAllUsers(){
        return userService.findAllUsers();
    }
    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id){
        return userService.findUserByUserId(id);
    }
    @PostMapping("")
    public UserModel createUser(@RequestBody UserModel userModel){
        return userService.createUser(userModel);
}
    @PutMapping("/{id}")
    public UserModel updateUser(@RequestBody UserModel userModel, @PathVariable Long id){
        return userService.updateUser(userModel, id);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
