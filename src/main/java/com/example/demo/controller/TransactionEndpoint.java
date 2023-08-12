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

//	@GetMapping("/transactions")
//	public ResponseStatus<List<Transaction>> findAllTransactions() throws IOException {
//
//		try {
//			// add if control if the list is empty to return no data found
//			return new ResponseStatus<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
//					transactionRepository.getAllTransactions());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//					HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
//		}
//	}
//
//	@GetMapping("/filtredTransactions")
//	public ResponseStatus<List<Transaction>> getFiltredTransactions(
//			@RequestParam(value = "amount", required = false) Double amount,
//			@RequestParam(value = "status", required = false) String status,
//			@RequestParam(value = "merchant", required = false) String merchant)throws ApiException, IOException {
//
//		TransactionFilter transactionFilter = new TransactionFilter(amount, merchant, status);
//		
//			return new ResponseStatus<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
//					transactionService.getTransactionByFilter(transactionFilter));
//		} 

	@GetMapping("/filtredSortedTransactions")
	public ResponseStatus<List<Transaction>> getFiltredSortedTransactions(
			@RequestParam(value = "amount", required = false) Double amount,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "merchant", required = false) String merchant,
			@RequestParam(required = false) String sortField,
			@RequestParam(required = false) Boolean sortOrder,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer perPage) throws ApiException, IOException {
		TransactionFilter transactionFilter = new TransactionFilter(amount, merchant, status);

		return new ResponseStatus<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				transactionService.getFilteredSortedTransactions(transactionFilter, sortField,sortOrder, page, perPage));

	}

}
