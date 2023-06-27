package com.carmel.surfScan.pingWorker;

import com.carmel.surfScan.dataModels.kafkaMessage.DomainJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PingKafkaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingKafkaListener.class);
    private DomainJob domainJob;

    @Autowired
    PingMessageProcessor pingMessageProcessor;

    @KafkaListener(topics = "ping", groupId = "surfScan", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupSurfScan(DomainJob domainJob) {
        LOGGER.info("Message {} received by a listener", domainJob);
        this.domainJob = domainJob;
        pingMessageProcessor.processJob(domainJob);
    }

    public DomainJob getDomainJob() {
        return domainJob;
    }

}
