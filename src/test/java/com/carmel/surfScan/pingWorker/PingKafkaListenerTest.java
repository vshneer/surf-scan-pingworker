package com.carmel.surfScan.pingWorker;

import com.carmel.surfScan.pingWorker.utils.TestKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class PingKafkaListenerTest {

    @Autowired
    private PingKafkaListener listener;


    @Autowired
    private TestKafkaProducer producer;

    @Value("${test.topic}")
    private String topic;

//    @Test
//    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
//            throws Exception {
//        String data = "example.com";
//
//        producer.send(topic, data);
//
//        boolean messageConsumed = listener.getLatch().await(10, TimeUnit.SECONDS);
//        assertTrue(messageConsumed);
//        assertThat(listener.getPayload(), containsString(data));
//    }
}
