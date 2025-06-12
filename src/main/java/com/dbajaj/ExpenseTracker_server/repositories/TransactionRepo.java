package com.dbajaj.ExpenseTracker_server.repositories;

import com.dbajaj.ExpenseTracker_server.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepo extends MongoRepository<Transaction,String> {
}
