package ex.blackdeer.kafka.dto;

import ex.blackdeer.kafka.command.CommandType;
import ex.blackdeer.kafka.device.DeviceType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

public class CommandRequest {

    private static final Logger logger = LoggerFactory.getLogger(CommandRequest.class);

    private String hubHash;
    private DeviceType deviceType;
    private int typeNo;
    private int roomNo;
    private int deviceNo;
    private CommandType commandType;
    private int value;

    public CommandRequest(@NonNull String hubHash,
                          @NonNull DeviceType deviceType,
                          int typeNo,
                          int roomNo,
                          int deviceNo,
                          @NonNull CommandType commandType,
                          int value) {
        this.hubHash = hubHash;
        this.deviceType = deviceType;
        this.typeNo = typeNo;
        this.roomNo = roomNo;
        this.deviceNo = deviceNo;
        this.commandType = commandType;
        this.value = value;
    }

    protected CommandRequest() {
    }

    public static CommandRequest jsonToCommandRequestOrNull(@NonNull JSONObject jsonObject) {
        try {
            String hubHash = jsonObject.getString("hubHash");
            DeviceType deviceType = DeviceType.valueOf(jsonObject.getString("deviceType"));
            int typeNo = jsonObject.getInt("typeNo");
            int roomNo = jsonObject.getInt("roomNo");
            int deviceNo = jsonObject.getInt("deviceNo");
            CommandType commandType = CommandType.valueOf(jsonObject.getString("commandType"));
            int value = jsonObject.getInt("value");
            return new CommandRequest(
                    hubHash,
                    deviceType,
                    typeNo,
                    roomNo,
                    deviceNo,
                    commandType,
                    value);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public String getHubHash() {
        return hubHash;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public int getTypeNo() {
        return typeNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getDeviceNo() {
        return deviceNo;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public int getValue() {
        return value;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hubHash", hubHash);
        jsonObject.put("deviceType", deviceType.name());
        jsonObject.put("typeNo", typeNo);
        jsonObject.put("roomNo", roomNo);
        jsonObject.put("deviceNo", deviceNo);
        jsonObject.put("commandType", commandType.name());
        jsonObject.put("value", value);
        return jsonObject;
    }

    protected void setHubHash(String hubHash) {
        this.hubHash = hubHash;
    }

    protected void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    protected void setTypeNo(int typeNo) {
        this.typeNo = typeNo;
    }

    protected void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    protected void setDeviceNo(int deviceNo) {
        this.deviceNo = deviceNo;
    }

    protected void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    protected void setValue(int value) {
        this.value = value;
    }
}
