package com.dataextractor.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.dataextractor.model.Transaction;

public class TranasctionDataMapper implements FieldSetMapper<Transaction> {
	public Transaction mapFieldSet(FieldSet fieldSet) {
		Transaction transaction = new Transaction();

		transaction.setTransactionDate(fieldSet.readString(0));
		transaction.setTransactionType(fieldSet.readString(1));
		transaction.setTransactionAmt(fieldSet.readBigDecimal(2));
		transaction.setAuthorizationNmbr(fieldSet.readInt(3));
		return transaction;
	}
}