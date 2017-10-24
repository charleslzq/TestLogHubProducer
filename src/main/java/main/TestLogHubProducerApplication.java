package main;

import com.github.charleslzq.aliyun.loghub.annotation.EnableLogHubConsumer;
import com.github.charleslzq.aliyun.loghub.annotation.EnableLogHubProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLogHubProducer
@EnableLogHubConsumer
public class TestLogHubProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLogHubProducerApplication.class, args);
    }
}
