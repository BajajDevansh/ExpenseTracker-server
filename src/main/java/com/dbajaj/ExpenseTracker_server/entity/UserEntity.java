package com.dbajaj.ExpenseTracker_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    @Id
    private String Id;
    private String fullName;
    @Indexed(unique = true)
    private String email;
    private String password;
}
