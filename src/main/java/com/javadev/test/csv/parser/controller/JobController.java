package com.javadev.test.csv.parser.controller;

/**
 * This class can be uncommented in case we want an API based job initiation trigger which
 * isn't the case as of now hence the comment-ification.
 * Please note that you will still need to send the filpath as CLI argument or the
 * spring batch file reader might throw a NPE.
 */

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/order")
public class JobController {

   /* @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PostMapping("/importorder")
    public void importCsvToConsole() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("Job started at: ", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }*/
}
