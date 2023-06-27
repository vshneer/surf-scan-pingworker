package com.carmel.surfScan.pingWorker;

import com.carmel.surfScan.dataModels.kafkaMessage.DomainJob;
import com.carmel.surfScan.pingWorker.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class PingProcessorTest extends Utils {
    @Autowired
    PingMessageProcessor pingMessageProcessor;
    @Test
    void given_JOB_when_PROCESS_then_RESULTS(){
        DomainJob domainJob = GET_SAMPLE_JOB();
        pingMessageProcessor.processJob(domainJob);
        assertFalse(pingMessageProcessor.getObservations().isEmpty());

    }
}
