package org.example.springdemo.conf.kafka.consumer;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.shaded.com.google.protobuf.Any;
import org.example.springdemo.controller.DBController;
import org.example.springdemo.model.Users;
import org.example.springdemo.repository.UsersRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumer {

    private final UsersRepository usersRepository;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public KafkaConsumer(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }


    @KafkaListener(topics = "userTest", groupId = "userGroupId")
    public void listen(ConsumerRecord<String, String> record) {

        String string = record.value();
        JSONObject jsonObject = new JSONObject(string);
        Users user = new Gson().fromJson(jsonObject.toString(), Users.class);

        System.out.println(user.toString());
        usersRepository.save(user);
    }
}


