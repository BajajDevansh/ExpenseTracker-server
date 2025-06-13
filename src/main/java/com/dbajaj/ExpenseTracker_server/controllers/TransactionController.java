package com.dbajaj.ExpenseTracker_server.controllers;

import com.dbajaj.ExpenseTracker_server.DTO.TransactionDTO;
import com.dbajaj.ExpenseTracker_server.entity.Transaction;
import com.dbajaj.ExpenseTracker_server.entity.UserEntity;
import com.dbajaj.ExpenseTracker_server.services.TransactionService;
import com.dbajaj.ExpenseTracker_server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    private UserEntity getCurrentUser(User principal){
        if(principal==null) throw new SecurityException("User not Authenticated");
        UserEntity user=userService.getByEmail(principal.getUsername());
        if(user==null) throw new SecurityException("No such user in application records");
        return user;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction,
                                            @AuthenticationPrincipal User principal){
        try{
            UserEntity user=getCurrentUser(principal);
            transaction.setUserId(user.getId());
            transactionService.saveTransactions(transaction);
            return ResponseEntity.ok("Transaction saved successfully");
        }catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Authorized");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(@AuthenticationPrincipal User principal){
        try{
            UserEntity user=getCurrentUser(principal);
            List<Transaction> transactions=transactionService.getTransactionsByUser(user.getId());
            return ResponseEntity.ok(transactions);
        } catch (SecurityException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
}
