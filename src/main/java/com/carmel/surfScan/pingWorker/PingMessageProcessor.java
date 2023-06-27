package com.carmel.surfScan.pingWorker;

import com.carmel.surfScan.dataModels.kafkaMessage.DomainJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class PingMessageProcessor {

    private static final Logger logger = LoggerFactory.getLogger(PingMessageProcessor.class);

    public List<String> getObservations() {
        return observations;
    }

    private List<String> observations = new ArrayList<>();

    public void processJob(DomainJob domainJob){
        logger.info("Ping working for {}", domainJob);

        LocalDateTime startTime = LocalDateTime.now();
        while ( ChronoUnit.SECONDS.between(startTime, LocalDateTime.now()) <= 10 ) {
            String observation = sendPingRequest(domainJob.getDomain());
            logger.info(observation);
            observations.add(observation);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // store results
    }
    private String sendPingRequest(String domain){
        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(domain, 443), 1000);
            }
            return "Host " + domain + "  is REACHABLE";
        } catch (IOException ex) {
            return "Host " + domain + " is UNREACHABLE";
        }

    }

}
