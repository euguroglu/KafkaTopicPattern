package com.enesuguroglu.Consumer;

import com.enesuguroglu.Producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
public class KafkaListener {

    @Autowired
    KafkaProducerService kafkaProducerService;

    //Create api post end-point to receive post message
    @org.springframework.kafka.annotation.KafkaListener(topicPattern = "kafka.*", groupId = "")
    public void send(String eventMessage, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws Exception {


        String deadlettertopic = String.format("%s_deadletter",topic);
        System.out.println(deadlettertopic);
        System.out.println(KafkaHeaders.RECEIVED_TOPIC);
        //Invoke kafka producer
        log.info("before-sendLibraryEvent"); // To observe asyncronus behaviour
        kafkaProducerService.sendOnFailure(deadlettertopic, eventMessage);
        log.info("after-sendLibraryEvent");
        //libraryEventProducer.sendLibraryEventSynchronous(libraryEvent)
    }


}