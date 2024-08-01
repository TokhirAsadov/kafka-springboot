package uz.tokhir.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import uz.tokhir.dto.Customer;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(String message){
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-topic", message);
//        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-topic",3,null, message);//sending specific partition
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                System.out.println("Sent message=[" + message +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            }
//            else {
//                System.out.println("Unable to send message=["+
//                        message+"] due to : " + ex.getMessage());
//            }
//        });

        kafkaTemplate.send("kafka-topic1",0,null, "salom");
        kafkaTemplate.send("kafka-topic1",1,null, "alik");
        kafkaTemplate.send("kafka-topic1",2,null, "java-");
        kafkaTemplate.send("kafka-topic1",2,null, "spring-");
        kafkaTemplate.send("kafka-topic1",2,null, "docker-");
        kafkaTemplate.send("kafka-topic1",3,null, "kubernetes");
        kafkaTemplate.send("kafka-topic1",4,null, "kafka");
    }

    public void sendEventToTopic(Customer customer){
        try {
            CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka-demo1", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + customer.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            customer.toString() + "] due to : " + ex.getMessage());
                }
            });
        }
        catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        }
    }
}
