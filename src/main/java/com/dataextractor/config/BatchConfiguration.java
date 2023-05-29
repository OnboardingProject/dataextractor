package com.dataextractor.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.dataextractor.job.JobCompletionNotificationListener;
import com.dataextractor.job.TransactionDataProcessor;
import com.dataextractor.mapper.TranasctionDataMapper;
import com.dataextractor.model.Transaction;

@Configuration
public class BatchConfiguration {

	// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<Transaction> reader() {
		FlatFileItemReader<Transaction> itemReader = new FlatFileItemReader<>();
		DefaultLineMapper<Transaction> lineMapper = new DefaultLineMapper<Transaction>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer("@");
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(new TranasctionDataMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.setResource(new FileSystemResource("resources/transactions.txt"));
		return itemReader;

	}

	@Bean
	public TransactionDataProcessor processor() {
		return new TransactionDataProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Transaction> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transaction>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO transaction (transactionDate,transactionType) VALUES (:transactionDate, :transactionType)").dataSource(dataSource)
				.build();
	}
	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean(name = "personJob")
	public Job importUserJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step1) {
		return new JobBuilder("importUserJob", jobRepository).incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			JdbcBatchItemWriter<Transaction> writer) {
		return new StepBuilder("step1", jobRepository).<Transaction, Transaction>chunk(10, transactionManager)
				.reader(reader()).processor(processor()).writer(writer).build();
	}
}
