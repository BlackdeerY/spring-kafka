package ex.blackdeer.kafka.consumer;

import ex.blackdeer.kafka.dto.CommandRequest;
import ex.blackdeer.kafka.dto.Shadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "shadowListner", topics = {"shadow"})
public class ShadowListner {

    private final Logger logger = LoggerFactory.getLogger(ShadowListner.class);

    private final TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();

    @KafkaHandler
    public void shadow(Shadow shadow) {
        logger.info(String.format("Kafka consume: [%stopic: %s,%smessage: %s%s]", System.lineSeparator(), "shadow", System.lineSeparator(), shadow.toJSONObject().toString(4), System.lineSeparator()));
    }
}
