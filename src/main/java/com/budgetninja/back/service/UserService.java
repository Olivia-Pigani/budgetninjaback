package com.budgetninja.back.service;

import com.budgetninja.back.model.CategoryModel;
import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.CategoryRepository;
import com.budgetninja.back.repository.RoleRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }
    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }
    public UserModel findUserByUserId(Long user_id){
        return userRepository.findById(user_id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"username not found")
        );
    }

    public UserModel updateUser(UserModel userModel, long user_id){
        UserModel existingUser= userRepository.findById(user_id).orElse(null);
        if( existingUser ==null){
            throw new RuntimeException("User not found");

        }else{
            existingUser.setUsername(userModel.getUsername());
            return userRepository.save(existingUser);
        }
}
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    }

