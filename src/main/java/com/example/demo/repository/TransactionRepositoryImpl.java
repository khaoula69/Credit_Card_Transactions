package com.example.demo.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	@Override
	public List<Transaction> getAllTransactions() throws IOException {
		ClassPathResource resource = new ClassPathResource("transactionsMock.json");
		//String json = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Transaction> transactions = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Transaction>>(){});

		return transactions;
	}
	

	 @Override
	    public List<Transaction> findByAmount(List<Transaction> transactions, Double amount) {
		 List<Transaction> filteredTransactions= transactions.stream().filter
				 (transaction -> transaction.getAmount().equals(amount)).toList();
	        return filteredTransactions;
	    }

	    @Override
	    public List<Transaction> findByMerchant(List<Transaction> transactions, String merchant) {
	    	 List<Transaction> filteredTransactions= transactions.stream().filter
					 (transaction -> transaction.getMerchant().equals(merchant)).toList();
	        return filteredTransactions;
	    }

	    @Override
	    public List<Transaction> findByStatus(List<Transaction> transactions, String status) {
	    	List<Transaction> filteredTransactions= transactions.stream().filter
	    			(transaction -> transaction.getStatus().equals(status)).toList();
	        return filteredTransactions;

}
}