package com.example.kafuka.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
   /**创建一个kafka管理类，相当于rabbitMQ的管理类rabbitAdmin,没有此bean无法自定义的使用adminClient创建topic*/
    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String,Object>  props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        KafkaAdmin kafkaAdmin = new KafkaAdmin(props);
        return kafkaAdmin;
    }
    /**kafka客户端，在spring中创建这个bean之后可以注入并且创建topic,用于集群环境，创建对个副本*/
    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }

    //创建TopicName为topic.quick.initial的Topic并设置分区数为8以及副本数为1
    @Bean//通过bean创建(bean的名字为initialTopic)
    public NewTopic initialTopic() {
        return new NewTopic("topic.quick.initial",8, (short) 1 );
    }
    @Bean
    public NewTopic updateTopic(){
        return  new NewTopic("topic.quick.initial",11,(short) 1);
    }

}
