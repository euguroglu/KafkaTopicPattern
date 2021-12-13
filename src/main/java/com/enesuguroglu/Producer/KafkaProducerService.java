package com.enesuguroglu.Producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOnFailure(String queue, String message) throws Exception {

        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(queue, message);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @SneakyThrows
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending data to kafka {}, data: {}", ex.getMessage(), message);
            }


            @Override
            public void onSuccess(SendResult<String, String> result) {

            }
        });

    }
}
