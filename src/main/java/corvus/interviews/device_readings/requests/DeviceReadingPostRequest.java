package corvus.interviews.device_readings.requests;

import corvus.interviews.device_readings.device.DeviceReading;

public class DeviceReadingPostRequest {

    private String id;
    private DeviceReading[] readings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceReading[] getReadings() {
        return readings;
    }

    public void setReadings(DeviceReading[] readings) {
        this.readings = readings;
    }

}
