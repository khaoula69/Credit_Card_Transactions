package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.demo.exceptions.ApiException;
import com.example.demo.model.ResponseStatus;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/creditCardTransaction")

public class TransactionEndpoint {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	TransactionService transactionService;

	// api to fetch all transactions
	@GetMapping("/transactions")
	public ResponseStatus<List<Transaction>> findAllTransactions() throws ApiException, IOException {
		return new ResponseStatus<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				transactionRepository.getAllTransactions());

	}
	// api to fetch transactions using filter

	@GetMapping("/filtredTransactions")
	public ResponseStatus<List<Transaction>> getFiltredTransactions(
			@RequestParam(value = "amount", required = false) Double amount,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "merchant", required = false) String merchant) throws ApiException, IOException {

		TransactionFilter transactionFilter = new TransactionFilter(amount, merchant, status);

		return new ResponseStatus<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				transactionService.getTransactionByFilter(transactionFilter));
	}

	// api to get transactions using filter, sorting and pagination if wanted
	@GetMapping("/filtredSortedTransactions")
	public ResponseStatus<List<Transaction>> getFiltredSortedTransactions(@RequestParam(required = false) Double amount,
			@RequestParam(required = false) String status, @RequestParam(required = false) String merchant,
			@RequestParam(required = false) String sortField, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer perPage) throws ApiException, IOException {
		TransactionFilter transactionFilter = new TransactionFilter(amount, merchant, status);

		return new ResponseStatus<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				transactionService.getFilteredSortedTransactions(transactionFilter, sortField, page, perPage));

	}

}
