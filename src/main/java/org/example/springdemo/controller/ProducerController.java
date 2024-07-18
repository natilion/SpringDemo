package org.example.springdemo.controller;

import org.example.springdemo.conf.kafka.producer.KafkaProducer;
import org.example.springdemo.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private final KafkaProducer kafkaProducer;

    public ProducerController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestParam String email,
                       @RequestParam String login,
                       @RequestParam String name,
                       @RequestParam String password) {

        kafkaProducer.sendMessage("userTest", new Users(email, login, name, password));
        return "Good";
    }
}
