//package com.project.races.timer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//@Service
//public class KafkaConsumer {
//    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void receiveMessage(MyMessage message) {
//        System.out.println(message.toString());
//    }
//}
