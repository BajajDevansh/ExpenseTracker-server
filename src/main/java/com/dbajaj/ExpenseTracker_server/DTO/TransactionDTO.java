package com.dbajaj.ExpenseTracker_server.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
public class TransactionDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private Double amount;
    private String type;
    private String description;
}
