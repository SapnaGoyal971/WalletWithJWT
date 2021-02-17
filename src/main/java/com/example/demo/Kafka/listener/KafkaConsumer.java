package com.example.demo.Kafka.listener;

import com.example.demo.Kafka.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test",groupId = "group_id", containerFactory = "kafkaListenerFactory")
    public void consume(String user){
        System.out.println("Consumed message " + user);
    }

    @KafkaListener(topics = "test", groupId ="group_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user){
        System.out.println("Consumed Json Message: "+ user);
    }

}
