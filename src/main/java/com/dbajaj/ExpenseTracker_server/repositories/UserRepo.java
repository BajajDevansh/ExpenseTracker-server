package com.dbajaj.ExpenseTracker_server.repositories;

import com.dbajaj.ExpenseTracker_server.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<UserEntity,String> {
    Optional<UserEntity> findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
