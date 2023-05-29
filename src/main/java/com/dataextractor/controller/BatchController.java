package com.dataextractor.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
public class BatchController {

	@Autowired
	@Qualifier("personJob")
	private Job personJob;

	@Autowired
	private JobLauncher launcher;

	/**
	 * Method to get all account details
	 * 
	 * @return List of Accounts
	 * @throws JobParametersInvalidException
	 * @throws JobInstanceAlreadyCompleteException
	 * @throws JobRestartException
	 * @throws JobExecutionAlreadyRunningException
	 */
	@GetMapping("extractandsave")
	public ResponseEntity<String> getAllAccounts() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		log.info("Batch job started");
		final JobExecution ext = launcher.run(personJob, new JobParameters());
		ext.getExecutionContext().size();
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
