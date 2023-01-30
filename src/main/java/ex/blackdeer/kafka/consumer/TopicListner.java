//package ex.blackdeer.kafka.consumer;
//
//import ex.blackdeer.kafka.dto.CommandRequest;
//import ex.blackdeer.kafka.dto.KafkaDTO;
//import ex.blackdeer.kafka.dto.Shadow;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.kafka.annotation.KafkaHandler;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//@Component
//@KafkaListener(id = "topicListner", topics = {"command", "shadow"})
//public class TopicListner {
//
//    private final Logger logger = LoggerFactory.getLogger(TopicListner.class);
//
//    private final TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
//
////    @KafkaHandler
////    public void command(CommandRequest commandRequest, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
////        logger.info(String.format("Kafka consume: [%stopic: %s,%smessage: %s%s]", System.lineSeparator(), topic, System.lineSeparator(), commandRequest.toJSONObject().toString(4), System.lineSeparator()));
////    }
////
////    @KafkaHandler
////    public void shadow(Shadow shadow, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
////        logger.info(String.format("Kafka consume: [%stopic: %s,%smessage: %s%s]", System.lineSeparator(), topic, System.lineSeparator(), shadow.toJSONObject().toString(4), System.lineSeparator()));
////    }
//
//    @KafkaHandler
//    public void listen(KafkaDTO kafkaDTO, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//        logger.info(String.format("Kafka consume: [%stopic: %s,%smessage: %s%s]", System.lineSeparator(), topic, System.lineSeparator(), kafkaDTO.toJSONObject().toString(4), System.lineSeparator()));
//    }
//}
