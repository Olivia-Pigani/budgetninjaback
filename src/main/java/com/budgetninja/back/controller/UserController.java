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
        return userService.findAll();
    }
    @PostMapping("")
    public UserModel createUser(@RequestBody UserModel userModel){
        return userService.save(userModel);
}
    @GetMapping("/{id}")
    public UserModel getUserById(Long id){
        return userService.findById(id);
    }

//    @GetMapping("")
//    public RoleModel addRole(@RequestBody RoleModel roleModel){
//        return userService.addRole(new RoleModel(null, "ADMIN"));
//    }
}
