package main;

import com.github.charleslzq.aliyun.loghub.annotation.EnableLogHubProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableLogHubProducer
public class TestLogHubProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLogHubProducerApplication.class, args);
    }
}
