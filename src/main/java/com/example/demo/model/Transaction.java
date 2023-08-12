package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Transaction {
	private Long id;
	private Double amount;
	private String merchant;
	private String status;
	private Date date;
	
	
	
	

}
