package ex.blackdeer.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic topicCommand() {
        return new NewTopic("command", 7, (short) 3);
    }

    @Bean
    public NewTopic topicShadow() {
        return new NewTopic("shadow", 7, (short) 3);
    }
}
