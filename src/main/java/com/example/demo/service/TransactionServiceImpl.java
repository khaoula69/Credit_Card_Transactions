package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public List<Transaction> getTransactionByFilter(TransactionFilter filter) throws IOException {
		List<Transaction> transactions = transactionRepository.getAllTransactions();
		List<Transaction> filteredTransactions = transactions.stream()
				.filter(transaction -> (filter.getAmount() == null || transaction.getAmount().toString().equals(filter.getAmount().toString()) )
						& (filter.getMerchant() == null || transaction.getMerchant().equals(filter.getMerchant()))
						& (filter.getStatus() == null || transaction.getStatus().equals(filter.getStatus())))
				.collect(Collectors.toList());

		return filteredTransactions;
	}

}
