package org.example.springdemo.conf.kafka.producer;

import org.example.springdemo.model.Users;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Users users) {
        kafkaTemplate.send(topic, users.toJSONString());
    }
}
