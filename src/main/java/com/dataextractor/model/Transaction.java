package com.dataextractor.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Transaction {
	
	

	/*2022-05-29@UPI@250@1234*/
	private int transactionId;
	private String transactionDate;
	private String transactionType;
	private BigDecimal transactionAmt;
	private Integer authorizationNmbr;
	public Transaction(int transactionId, String transactionDate, String transactionType) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
	}
	
	

}
