package com.example.kafuka.controller;

import com.example.kafuka.pojo.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@Slf4j
public class SendMessageController {
    private Logger logger = LoggerFactory.getLogger(SendMessageController.class);

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @GetMapping("send/{message}")
    public void sendMessage(@PathVariable String message) {
        this.kafkaTemplate.send("test", new Message("mrbird", message));
    }
}
