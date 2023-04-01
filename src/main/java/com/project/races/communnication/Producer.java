//package com.project.races.timer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducer {
//    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
//
//    @Autowired
//    private KafkaTemplate<String, MyMessage> kafkaTemplate;
//
//    @Value("${kafka.topic.name}")
//    private String topicName;
//
//    public void sendMessage(MyMessage message) {
//        logger.info("____SEND" + message);
//        kafkaTemplate.send(topicName, message);
//    }
//}
