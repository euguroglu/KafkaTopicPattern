package com.enesuguroglu.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;



@Configuration
public class AutoCreateConfig {

    @Bean
    public void getTopic(){
        NewTopic deadlettertopic = TopicBuilder.name("trial")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
