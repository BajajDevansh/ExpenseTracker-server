package com.dbajaj.ExpenseTracker_server.controllers;

import com.dbajaj.ExpenseTracker_server.DTO.TransactionDTO;
import com.dbajaj.ExpenseTracker_server.entity.Transaction;
import com.dbajaj.ExpenseTracker_server.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/new-transaction")
    public Transaction addTransaction(@RequestBody TransactionDTO transactionDTO){
        return transactionService.saveTransactions(transactionDTO);
    }
    @GetMapping("/get-transaction/{userId}")
    public List<Transaction> getByUser(@PathVariable String userId){
        return transactionService.getTransactionsByUser(userId);
    }
}
