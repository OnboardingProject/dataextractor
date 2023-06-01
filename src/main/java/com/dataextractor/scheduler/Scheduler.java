package com.dataextractor.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Scheduler {
	
	@Autowired
	@Qualifier("personJob")
	private Job personJob;
	
	@Autowired
	private JobLauncher launcher;
	
   @Scheduled(cron = "0 30 10 * * ?")
   public void cronJobSch() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	    log.info("Batch job started");
		final JobExecution ext = launcher.run(personJob, new JobParameters());
		ext.getExecutionContext().size();
     
   }
}