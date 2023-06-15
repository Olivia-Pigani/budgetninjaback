package com.budgetninja.back.controller;

import com.budgetninja.back.model.NinjaModel;
import com.budgetninja.back.service.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/users")
public class NinjaController {
    private final NinjaService ninjaService;

    public NinjaController(NinjaService userService){
        this.ninjaService = userService;
    }
    @GetMapping("")
    public List<NinjaModel> getAllUsers(){
        return ninjaService.findAllUsers();
    }
    @PostMapping("")
    public NinjaModel createUser(@RequestBody NinjaModel userModel){
        return ninjaService.save(userModel);
}
    @GetMapping("/{user_id}")
    public NinjaModel getUserById(@PathVariable Long user_id){
        return ninjaService.findUserByUserId(user_id);
    }

}
