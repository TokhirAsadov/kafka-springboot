package uz.tokhir.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import uz.tokhir.dto.Customer;

@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

//    @KafkaListener(topics = "kafka-topic",groupId = "kafka-group1")
//    public void consumeEvent(Customer customer){
//        logger.info("consumer1 consume the event -> {}",customer.toString());
//    }


    //consume data from specific partition
    @KafkaListener(topics = "kafka-topic1",groupId = "kafka-group1",topicPartitions = {@TopicPartition(topic = "kafka-topic1",partitions = {"2"})})
    public void consumeEvent(String customer){
        logger.info("consumer1 consume the event -> {}",customer.toString());
    }

//    @KafkaListener(topics = "my-first-topic",groupId = "mf-group-new2")
//    public void consume2(String message){
//        logger.info("consumer2 consume the message -> {}",message);
//    }
//
//    @KafkaListener(topics = "my-first-topic",groupId = "mf-group-new2")
//    public void consume3(String message){
//        logger.info("consumer3 consume the message -> {}",message);
//    }
//
//    @KafkaListener(topics = "my-first-topic",groupId = "mf-group-new2")
//    public void consume4(String message){
//        logger.info("consumer4 consume the message -> {}",message);
//    }
}
