package com.example.demo.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

import com.example.demo.exceptions.ApiException;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	@Override
	public List<Transaction> getAllTransactions() throws ApiException, IOException {
		ClassPathResource resource = new ClassPathResource("transactionsMock.json");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Transaction> transactions = objectMapper.readValue(resource.getInputStream(),
				new TypeReference<List<Transaction>>() {
				});

		return transactions;
	}

	// to use if i want to filter by amount without using the transactionFilter
	@Override
	public List<Transaction> findByAmount(List<Transaction> transactions, Double amount) {
		List<Transaction> filteredTransactions = transactions.stream()
				.filter(transaction -> transaction.getAmount().equals(amount)).toList();
		return filteredTransactions;
	}

	// to use if i want to filter by merchant without using the transactionFilter
	@Override
	public List<Transaction> findByMerchant(List<Transaction> transactions, String merchant) {
		List<Transaction> filteredTransactions = transactions.stream()
				.filter(transaction -> transaction.getMerchant().equals(merchant)).toList();
		return filteredTransactions;
	}

	// to use if i want to filter by status without using the transactionFilter
	@Override
	public List<Transaction> findByStatus(List<Transaction> transactions, String status) {
		List<Transaction> filteredTransactions = transactions.stream()
				.filter(transaction -> transaction.getStatus().equals(status)).toList();
		return filteredTransactions;

	}
}