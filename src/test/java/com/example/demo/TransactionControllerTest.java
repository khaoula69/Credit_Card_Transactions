package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.TransactionEndpoint;
import com.example.demo.exceptions.ApiException;
import com.example.demo.model.ResponseStatus;
import com.example.demo.service.TransactionService;

public class TransactionControllerTest {

	 @Mock
	    private TransactionService service;
	    @InjectMocks
	    private TransactionEndpoint controller;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testGetTransactions() throws ApiException, IOException {
	        ResponseStatus<?> response = controller.getFiltredSortedTransactions(100.0, "SUCCESS", "Some Merchant", "amount", 1, 10);

	        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());
	        assertThat(response.getMessage()).isEqualTo(HttpStatus.OK.getReasonPhrase());
	    }

}
