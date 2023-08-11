package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;


public interface TransactionService {
	
    List<Transaction> getTransactionByFilter(TransactionFilter filter) throws IOException;


}
