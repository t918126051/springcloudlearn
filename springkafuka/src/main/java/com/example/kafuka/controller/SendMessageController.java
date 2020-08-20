package com.example.kafuka.controller;

import com.example.kafuka.pojo.Message;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class SendMessageController {
    private Logger logger = LoggerFactory.getLogger(SendMessageController.class);
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
//    @Autowired
//    private KafkaTemplate<String, Message> kafkaComplexTemplate;

    @RequestMapping("/send/{message}")
    public void  sendMessage(@PathVariable String message){
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("test",message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
            }
        });
    }

//    @RequestMapping("/sendcomplex/{message}")
//    public void  sendComplexMessage(@PathVariable String message){
//        this.kafkaComplexTemplate.send("test",new Message("ZS",message));
//
//    }
}
