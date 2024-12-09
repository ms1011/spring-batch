package com.example.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job helloJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("HelloJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step helloStep(JobRepository jobRepository, Tasklet tasklet, PlatformTransactionManager transactionManager) {
        return new StepBuilder("HelloStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Tasklet helloTasklet() {
        return (contribution, chunkContext) ->
        {
            System.out.println("Hello! 스프링 배치 연습");
            return RepeatStatus.FINISHED;
        };
    }
}
