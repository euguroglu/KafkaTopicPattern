package com.enesuguroglu.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.KafkaHeaders;



@Configuration
public class AutoCreateConfig {


    @KafkaListener(topicPattern = "kafka.*",groupId = "test-grp")
    @Bean
    public void getTopic(){
        String topic = KafkaHeaders.RECEIVED_TOPIC;
        NewTopic deadlettertopic = TopicBuilder.name(String.format("%s_deadletter",topic))
                .partitions(1)
                .replicas(1)
                .build();
    }
}
