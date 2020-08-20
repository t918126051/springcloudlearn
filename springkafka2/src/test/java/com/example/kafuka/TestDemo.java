package com.example.kafuka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    private Logger logger = LoggerFactory.getLogger(TestDemo.class);
    @Autowired
    private AdminClient adminClient;
    @Test
    public void test1(){
        NewTopic newTopic = new NewTopic("topic.manual.create", 10, (short) 1);
        adminClient.createTopics(Arrays.asList(newTopic));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ListTopicsResult listTopics = adminClient.listTopics();
        Set<String> topics = listTopics.names().get();
        for (String topic : topics){
            System.out.println(topic);
        }
    }
}
