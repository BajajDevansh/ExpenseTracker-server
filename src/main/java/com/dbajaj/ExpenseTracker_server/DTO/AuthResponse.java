package com.dbajaj.ExpenseTracker_server.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class AuthResponse {
    private String jwtToken;
    private String userId;
    private String username;

    public AuthResponse(String jwtToken, String userId, String username) {
        this.jwtToken = jwtToken;
        this.userId = userId;
        this.username = username;
    }
}
