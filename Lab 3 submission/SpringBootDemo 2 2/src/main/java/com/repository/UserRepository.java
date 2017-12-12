package com.repository;

import com.entity.GroupT;
import com.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByEmailAndPassword(String email, String password);

    List<User> findUserById(Integer id);

    User findUserByEmail(String email);

}

