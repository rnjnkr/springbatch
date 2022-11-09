package com.javadev.test.csv.parser.config.steplistener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class StepListener extends StepExecutionListenerSupport {
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Total Records processed: #" + stepExecution.getWriteCount());
        System.out.println("Total Records skipped: #" + stepExecution.getSkipCount());
        return null;
    }
}
