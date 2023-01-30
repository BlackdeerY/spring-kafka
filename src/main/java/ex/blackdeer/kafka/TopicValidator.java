//package ex.blackdeer.kafka;
//
//import org.springframework.lang.NonNull;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class TopicValidator {
//
//    public static boolean isValidTopic(@NonNull String topic) {
//        Matcher matcher = Pattern.compile("^[\\w\\.\\-_]+$").matcher(topic);
//        return matcher.find();
//    }
//}
