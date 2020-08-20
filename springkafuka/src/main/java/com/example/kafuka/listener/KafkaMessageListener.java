package com.example.kafuka.listener;

import com.example.kafuka.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen(String message) {
//        logger.info("接收消息: {}", message);
//    }

    /**需要指定特定分区*/
    @KafkaListener(groupId = "test-consumer",topicPartitions = @TopicPartition(topic = "test",partitionOffsets = {@PartitionOffset(partition = "1", initialOffset = "0" )}))
    public void listen(@Payload String message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info("接收消息: {}，partition：{}", message, partition);
    }

//    /**如果不需要指定initialOffset，上面代码可以简化为：
//    @KafkaListener(groupId = "test-consumer",
//            topicPartitions = @TopicPartition(topic = "test", partitions = { "0", "1" }))*/
//    /**需要指定特定分区*/
//    @KafkaListener(groupId = "test-consumer",topicPartitions = @TopicPartition(topic = "test",partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0" )}))
//    public void listen(@Payload Message message,
//                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
//
//        logger.info("从"+message.getFrom()+"接收消息: {}，partition：{}", message.getMessage(), partition);
//    }
//    @KafkaListener(topics = "test", groupId = "test-consumer")
//    public void listen(Message message) {
//        logger.info("接收消息: {}", message);
//    }
}

