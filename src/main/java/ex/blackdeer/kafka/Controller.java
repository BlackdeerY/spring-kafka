package ex.blackdeer.kafka;

import ex.blackdeer.kafka.dto.CommandRequest;
import ex.blackdeer.kafka.dto.Shadow;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/kafka/rest")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final ProduceService produceService;

    @Autowired
    public Controller(ProduceService produceService) {
        this.produceService = produceService;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("parameter '%s' has invalid type.", exception.getName()));
    }

    @PostMapping("/command")
    public ResponseEntity sendCommand(@RequestParam(required = false) String key,
                                      @RequestParam JSONObject message) {
        CommandRequest commandRequest = CommandRequest.jsonToCommandRequestOrNull(message);
        if (commandRequest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message is invalid for jsonCommandRequest.");
        }
        produceService.sendCommand(key, commandRequest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/shadow")
    public ResponseEntity sendShadow(@RequestParam(required = false) String key,
                                     @RequestParam JSONObject message) {
        Shadow shadow = Shadow.jsonToShadowOrNull(message);
        if (shadow == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("message is invalid for jsonShadow.");
        }
        produceService.sendShadow(key, shadow);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
