package name.hudelmaier.azureeventhubskafkaexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RestController
public class AzureEventHubsKafkaExampleApplication {

	private static final Logger logger = LoggerFactory.getLogger(AzureEventHubsKafkaExampleApplication.class);

	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public AzureEventHubsKafkaExampleApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@GetMapping("/send-message")
	public void sendMessage() throws InterruptedException, ExecutionException, TimeoutException {
		kafkaTemplate.send("test-topic", "hello", "world").get(
				15, TimeUnit.SECONDS);
	}

	@KafkaListener(topics = "test-topic", groupId = "$Default")
	public void receive(String payload) {
		logger.info("Received message with value: " + payload);
	}

	public static void main(String[] args) {
		SpringApplication.run(AzureEventHubsKafkaExampleApplication.class, args);
	}
}
