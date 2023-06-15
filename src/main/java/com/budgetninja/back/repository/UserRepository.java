package com.budgetninja.back.repository;

import com.budgetninja.back.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
//    List<UserModel> findByUserId(Long user_id);

}
