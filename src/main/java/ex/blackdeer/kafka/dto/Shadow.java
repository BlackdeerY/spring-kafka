package ex.blackdeer.kafka.dto;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//public class Shadow extends KafkaDTO {
public class Shadow {

    private static final Logger logger = LoggerFactory.getLogger(Shadow.class);

    private String hubHash;
    private List<DeviceStatus> deviceStatusList;

    public Shadow(@NonNull String hubHash,
                  @NonNull List<DeviceStatus> deviceStatusList) {
        this.hubHash = hubHash;
        this.deviceStatusList = deviceStatusList;
    }

    public static Shadow jsonToShadowOrNull(@NonNull JSONObject jsonObject) {
        try {
            String hubHash = jsonObject.getString("hubHash");
            JSONArray deviceStatusJsonArray = jsonObject.getJSONArray("deviceStatusList");
            List<DeviceStatus> deviceStatusList = new ArrayList<>(deviceStatusJsonArray.length());
            for (int i = 0; i < deviceStatusJsonArray.length(); ++i) {
                JSONObject deviceStatusJsonObject = deviceStatusJsonArray.getJSONObject(i);
                DeviceStatus deviceStatus = DeviceStatus.jsonToDeviceStatusOrNull(deviceStatusJsonObject);
                deviceStatusList.add(deviceStatus);
            }
            return new Shadow(hubHash, deviceStatusList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    protected Shadow() {
    }

    public String getHubHash() {
        return hubHash;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hubHash", hubHash);
        JSONArray deviceStatusJsonArray = new JSONArray(deviceStatusList.size());
        for (DeviceStatus deviceStatus : deviceStatusList) {
            deviceStatusJsonArray.put(deviceStatus.toJSONObject());
        }
        jsonObject.put("deviceStatusList", deviceStatusJsonArray);
        return jsonObject;
    }

    public List<DeviceStatus> getDeviceStatusList() {
        return Collections.unmodifiableList(deviceStatusList);
    }

    protected void setHubHash(String hubHash) {
        this.hubHash = hubHash;
    }

    protected void setDeviceStatusList(List<DeviceStatus> deviceStatusList) {
        this.deviceStatusList = deviceStatusList;
    }
}
