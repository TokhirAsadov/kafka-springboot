package uz.tokhir.kafkaproducerexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import uz.tokhir.dto.Customer;
import uz.tokhir.service.KafkaMessagePublisher;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaProducerExampleApplicationTests {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @DynamicPropertySource
    public static void initKafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaMessagePublisher publisher;

    @Test
    void testSendEventToTopic() {
        publisher.sendEventToTopic(new Customer(201,"Test_Customer","test@email.com","test-number"));
        await().pollInterval(Duration.ofSeconds(3))
                .atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            // assert statement
        });
    }

}
