package ex.blackdeer.kafka.dto;

import ex.blackdeer.kafka.device.DeviceProperty;
import ex.blackdeer.kafka.device.DeviceType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeviceStatus {

    private static final Logger logger = LoggerFactory.getLogger(DeviceStatus.class);

    private DeviceType deviceType;
    private int typeNo;
    private int roomNo;
    private int deviceNo;
    private Map<DeviceProperty, Object> values;

    public DeviceStatus(@NonNull DeviceType deviceType,
                        int typeNo,
                        int roomNo,
                        int deviceNo,
                        @NonNull Map<DeviceProperty, Object> values) {
        this.deviceType = deviceType;
        this.typeNo = typeNo;
        this.roomNo = roomNo;
        this.deviceNo = deviceNo;
        this.values = values;
    }

    public static DeviceStatus jsonToDeviceStatusOrNull(@NonNull JSONObject jsonObject) {
        try {
            DeviceType deviceType = DeviceType.valueOf(jsonObject.getString("deviceType"));
            int typeNo = jsonObject.getInt("typeNo");
            int roomNo = jsonObject.getInt("roomNo");
            int deviceNo = jsonObject.getInt("deviceNo");
            JSONObject valuesJson = jsonObject.getJSONObject("values");
            Map<DeviceProperty, Object> values = new HashMap<>(valuesJson.length());
            for (String key : valuesJson.keySet()) {
                values.put(DeviceProperty.valueOf(key), valuesJson.get(key));
            }
            return new DeviceStatus(
                    deviceType,
                    typeNo,
                    roomNo,
                    deviceNo,
                    values);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    protected DeviceStatus() {
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

    public Map<DeviceProperty, Object> getValues() {
        return Collections.unmodifiableMap(values);
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deviceType", this.deviceType.name());
        jsonObject.put("typeNo", this.typeNo);
        jsonObject.put("roomNo", this.roomNo);
        jsonObject.put("deviceNo", this.deviceNo);
        JSONObject values = new JSONObject();
        for (DeviceProperty deviceProperty : this.values.keySet()) {
            values.put(deviceProperty.name(), this.values.get(deviceProperty));
        }
        jsonObject.put("values", values);
        return jsonObject;
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

    protected void setValues(Map<DeviceProperty, Object> values) {
        this.values = values;
    }
}
