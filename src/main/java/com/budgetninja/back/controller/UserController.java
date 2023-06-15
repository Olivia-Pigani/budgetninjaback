package com.budgetninja.back.controller;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.model.RoleModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.service.CategoryService;
import com.budgetninja.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @PostMapping("")
    public UserModel createUser(@RequestBody UserModel userModel){
        return userService.save(userModel);
}
    @GetMapping("/{user_id}")
    public UserModel getUserById(@PathVariable Long user_id){
        return userService.findUserByUserId(user_id);
    }


}
