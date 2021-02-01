package com.example.demo.springbatch.config;

import com.example.demo.springbatch.tasklets.LinesProcessor;
import com.example.demo.springbatch.tasklets.LinesReader;
import com.example.demo.springbatch.tasklets.LinesWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class TaskletsConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }

    @Bean
    public LinesReader linesReader() {
        return new LinesReader();
    }

    @Bean
    public LinesProcessor linesProcessor() {
        return new LinesProcessor();
    }

    @Bean
    public LinesWriter linesWriter() {
        return new LinesWriter();
    }

    @Bean
    protected Step readLines() {
        return steps
                .get("readLines")
                .tasklet(linesReader())
                .build();
    }

    @Bean
    protected Step processLines() {
        return steps
                .get("processLines")
                .tasklet(linesProcessor())
                .build();
    }

    @Bean
    protected Step writeLines() {
        return steps
                .get("writeLines")
                .tasklet(linesWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobs
                .get("taskletsJob")
                .start(readLines())
                .next(processLines())
                .next(writeLines())
                .build();
    }
}
