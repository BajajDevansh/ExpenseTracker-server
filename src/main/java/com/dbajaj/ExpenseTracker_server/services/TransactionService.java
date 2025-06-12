package com.dbajaj.ExpenseTracker_server.services;

import com.dbajaj.ExpenseTracker_server.DTO.TransactionDTO;
import com.dbajaj.ExpenseTracker_server.entity.Transaction;
import com.dbajaj.ExpenseTracker_server.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepo;
    @Transactional
    public Transaction saveTransactions(TransactionDTO transactionDTO){
        try{
            Transaction transaction = new Transaction();
            transaction.setUserId(transactionDTO.getUserId());
            transaction.setDate(LocalDateTime.now());
            transaction.setType(transactionDTO.getType());
            transaction.setDescription(transactionDTO.getDescription());
            transaction.setAmount(transaction.getAmount());
            transactionRepo.save(transaction);
            return transaction;
        } catch (Exception e) {
            throw new RuntimeException("Error saving transaction: "+e.getMessage());
        }
    }
    public List<Transaction> getTransactionsByUser(String userId){
        return transactionRepo.findAll().stream().filter
                (tr->tr.getUserId().equals(userId)).toList();
    }

}
