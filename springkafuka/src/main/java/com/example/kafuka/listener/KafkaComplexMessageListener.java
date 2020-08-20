//package com.example.kafuka.listener;
//
//import com.example.kafuka.pojo.Message;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaComplexMessageListener {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @KafkaListener(groupId = "test-consumer",topicPartitions = @TopicPartition(topic = "test",partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0" )}))
//    public void listen( Message message) {
//        logger.info("接收消息: {}", message.getMessage());
//    }
//}
