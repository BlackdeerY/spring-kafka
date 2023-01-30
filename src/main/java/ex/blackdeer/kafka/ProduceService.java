package ex.blackdeer.kafka;

import ex.blackdeer.kafka.dto.CommandRequest;
import ex.blackdeer.kafka.dto.Shadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ProduceService {

    private static final Logger logger = LoggerFactory.getLogger(ProduceService.class);

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public void send(@NonNull String topic,
                     @Nullable String keyOrNull,
                     @NonNull String message) {
        // topic은 이미 검증되어 들어오도록
        logger.info(String.format("Kafka produce: [%stopic: %s,%skey: %s,%smessage: %s%s]", System.lineSeparator(), topic, System.lineSeparator(), keyOrNull, System.lineSeparator(), message, System.lineSeparator()));
        this.kafkaTemplate.send(topic, keyOrNull, message);
    }

    public void sendCommand(@Nullable String keyOrNull,
                            @NonNull CommandRequest commandRequest) {
        String topic = "command";
        String message = commandRequest.toJSONObject().toString(4);
        logger.info(String.format("Kafka produce: [%stopic: %s,%skey: %s,%scommandRequest: %s%s]", System.lineSeparator(), topic, System.lineSeparator(), keyOrNull, System.lineSeparator(), message, System.lineSeparator()));
        this.kafkaTemplate.send("command", keyOrNull, commandRequest);
    }

    public void sendShadow(@Nullable String keyOrNull,
                           @NonNull Shadow shadow) {
        String topic = "shadow";
        String message = shadow.toJSONObject().toString(4);
        logger.info(String.format("Kafka produce: [%stopic: %s,%skey: %s,%sshadow: %s%s]", System.lineSeparator(), topic, System.lineSeparator(), keyOrNull, System.lineSeparator(), message, System.lineSeparator()));
        this.kafkaTemplate.send("shadow", keyOrNull, shadow);
    }

}
