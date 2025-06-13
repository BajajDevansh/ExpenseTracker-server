package com.dbajaj.ExpenseTracker_server.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
