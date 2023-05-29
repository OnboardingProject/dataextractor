package com.dataextractor.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.dataextractor.model.Transaction;

public class TransactionDataProcessor implements ItemProcessor<Transaction, Transaction> {

	private static final Logger log = LoggerFactory.getLogger(TransactionDataProcessor.class);

	@Override
	public Transaction process(final Transaction data) throws Exception {
		// final String firstName = person.getFirstName().toUpperCase();
		// final String lastName = person.getLastName().toUpperCase();

		/// final Person transformedPerson = new Person(firstName, lastName);

		 log.info("Converting (" + data + ") into (" + data + ")");

		return data;
	}

}
