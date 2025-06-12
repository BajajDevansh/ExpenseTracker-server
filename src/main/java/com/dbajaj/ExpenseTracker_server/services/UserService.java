package com.dbajaj.ExpenseTracker_server.services;

import com.dbajaj.ExpenseTracker_server.DTO.UserDTO;
import com.dbajaj.ExpenseTracker_server.entity.UserEntity;
import com.dbajaj.ExpenseTracker_server.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public UserEntity registerNewUser(UserDTO userDTO){
        if(userRepo.existsByEmail(userDTO.getEmail())){
            throw new RuntimeException("Email Already Exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(user.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepo.save(user);
        return user;
    }
}
