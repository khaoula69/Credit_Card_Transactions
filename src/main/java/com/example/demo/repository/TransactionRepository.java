package com.example.demo.repository;

import java.io.IOException;
import java.util.List;

import com.example.demo.exceptions.ApiException;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;

public interface TransactionRepository {
    List<Transaction> getAllTransactions() throws ApiException, IOException;
    
    List<Transaction> findByAmount(List<Transaction> transactions, Double amount);
    List<Transaction> findByMerchant(List<Transaction> transactions, String merchant);
    List<Transaction> findByStatus(List<Transaction> transactions, String status);
    
    


}
