package com.carmel.surfScan.pingWorker.utils;

import com.carmel.surfScan.dataModels.kafkaMessage.DomainJob;

public class Utils {
    protected static String DOMAIN = "example.com";
    protected static DomainJob GET_SAMPLE_JOB(){
        DomainJob domainJob = new DomainJob();
        domainJob.setDomain(DOMAIN);
        domainJob.setJobPrimaryId(1);
        return domainJob;
    }
}
