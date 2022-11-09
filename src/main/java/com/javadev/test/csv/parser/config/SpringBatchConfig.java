package com.javadev.test.csv.parser.config;

import com.javadev.test.csv.parser.config.customitemwriter.ConsoleWriter;
import com.javadev.test.csv.parser.config.itemprocessor.OrdersProcessor;
import com.javadev.test.csv.parser.config.steplistener.StepListener;
import com.javadev.test.csv.parser.model.Order;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.validation.BindException;

import java.util.Objects;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    Environment environment;

    @Bean
    public FlatFileItemReader<Order> fileReader() {
        return new FlatFileItemReaderBuilder<Order>()
                .resource(new FileSystemResource(Objects.requireNonNull(environment.getProperty("file"))))
                .name("orderReader")
                .linesToSkip(1)
                .lineMapper(lineMapper())
                .build();
    }

    @Bean
    public LineMapper<Order> lineMapper() {
        DefaultLineMapper<Order> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "amount", "currency", "comment");

        BeanWrapperFieldSetMapper<Order> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Order.class); // amount = 323 or "323" are handled here

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public OrdersProcessor processor() {
        return new OrdersProcessor();
    }

    @Bean
    public ConsoleWriter<Order> writer() {
        return new ConsoleWriter<>();

    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-read-write-step")
                .<Order, Order>chunk(1000)
                .reader(fileReader())
                .faultTolerant()
                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .skip(RuntimeException.class) //skip any exception i.e bad record and continue further
                .noRetry(RuntimeException.class)
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .skipPolicy(new AlwaysSkipItemSkipPolicy())
                .skip(RuntimeException.class)
                .noRetry(RuntimeException.class)
                .taskExecutor(taskExecutor())
                .listener(new StepListener())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("processOrder")
                .flow(step1())
                .end().build();
    }
}
