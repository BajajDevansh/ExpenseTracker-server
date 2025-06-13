package com.dbajaj.ExpenseTracker_server.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwtToken;
    private String userId;
    private String username;
}
