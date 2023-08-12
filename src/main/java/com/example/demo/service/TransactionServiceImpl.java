package com.example.demo.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ApiException;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;
import com.example.demo.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public List<Transaction> getTransactionByFilter(TransactionFilter filter) throws ApiException, IOException {
		List<Transaction> transactions = transactionRepository.getAllTransactions();
		List<Transaction> filteredTransactions = transactions.stream()
				.filter(transaction -> (filter.getAmount() == null
						|| transaction.getAmount().toString().equals(filter.getAmount().toString()))
						&& (filter.getMerchant() == null || transaction.getMerchant().equals(filter.getMerchant()))
						&& (filter.getStatus() == null || transaction.getStatus().equals(filter.getStatus())))
				.collect(Collectors.toList());

		return filteredTransactions;
	}

	private List<Transaction> sortByField(List<Transaction> transactions, String sortingField) throws ApiException {

		Comparator<Transaction> comparator = Comparator.comparing(Transaction::getId);
		if ("amount".equalsIgnoreCase(sortingField)) {
			comparator = Comparator.comparing(Transaction::getAmount);
		} else if ("merchant".equalsIgnoreCase(sortingField)) {
			comparator = Comparator.comparing(Transaction::getMerchant);
		} else if ("status".equalsIgnoreCase(sortingField)) {
			comparator = Comparator.comparing(Transaction::getStatus);
		} else {
			throw new ApiException(HttpStatus.BAD_REQUEST.value(), "INVALID_SORT_FIELD_VALUE");
		}
		Collections.sort(transactions, comparator);
		return transactions;
	}

	@Override
	public List<Transaction> perPageTransactions(List<Transaction> transactions, Integer page, Integer perPage)
			throws ApiException, IOException {
		if (page <= 0 || page >= transactions.size())
			throw new ApiException(HttpStatus.BAD_REQUEST.value(), "INVALID_PAGE_NUMBER");
		if (perPage <= 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST.value(), "INVALID_SIZE");

		}
		int startIndex = (page - 1) * perPage;
		int endIndex = Math.min(startIndex + perPage, transactions.size());
		transactions = transactions.subList(startIndex, endIndex);

		return transactions;

	}

	@Override
	public List<Transaction> getFilteredSortedTransactions(TransactionFilter filter, String sortOrder, Integer page,
			Integer perPage) throws ApiException, IOException {

		List<Transaction> transactions = (filter != null) ? getTransactionByFilter(filter)
				: transactionRepository.getAllTransactions();
		if (sortOrder != null) {
			sortByField(transactions, sortOrder);
		}

		if (page != null && perPage != null) {
			transactions = perPageTransactions(transactions, page, perPage);
		}
		return transactions;

	}

}
