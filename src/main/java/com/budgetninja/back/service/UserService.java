package com.budgetninja.back.service;

import com.budgetninja.back.model.UserModel;
import com.budgetninja.back.repository.RoleRepository;
import com.budgetninja.back.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public UserModel findUserByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
        );
    }

    public UserModel updateUser(UserModel userModel, long id) {
        UserModel existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        } else {
            existingUser.setUsername(userModel.getUsername());
            existingUser.setPassword(userModel.getPassword());
            existingUser.setEmail(userModel.getEmail());
            existingUser.setValidated(userModel.getValidated());
            return userRepository.save(existingUser);
        }
    }

    public UserModel createUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

