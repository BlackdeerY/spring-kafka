package ex.blackdeer.kafka.consumer;

import ex.blackdeer.kafka.dto.CommandRequest;
import ex.blackdeer.kafka.dto.Shadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(clientIdPrefix = "shadowListner", topics = {"shadow"}, concurrency = "7", groupId = "spring-kafka")
public class ShadowListner {

    private final Logger logger = LoggerFactory.getLogger(ShadowListner.class);

    private final TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();

    @KafkaHandler
    public void shadow(Shadow shadow,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) String partitionId,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp) {
        logger.info(String.format("Kafka consume: [%stopic: %s,%skey: %s,%spartitionId: %s,%stimestamp: %s,%smessage: %s%s]",
                System.lineSeparator(),
                topic,
                System.lineSeparator(),
                key,
                System.lineSeparator(),
                partitionId,
                System.lineSeparator(),
                timestamp,
                System.lineSeparator(),
                shadow.toJSONObject().toString(4),
                System.lineSeparator()));
    }
}
