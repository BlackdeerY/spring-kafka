package ex.blackdeer.kafka.consumer;

import ex.blackdeer.kafka.dto.CommandRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "commandListner", topics = {"command"})
public class CommandListner {

    private final Logger logger = LoggerFactory.getLogger(CommandListner.class);

    private final TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();

    @KafkaHandler
    public void command(CommandRequest commandRequest) {
        logger.info(String.format("Kafka consume: [%stopic: %s,%smessage: %s%s]", System.lineSeparator(), "command", System.lineSeparator(), commandRequest.toJSONObject().toString(4), System.lineSeparator()));
    }
}
