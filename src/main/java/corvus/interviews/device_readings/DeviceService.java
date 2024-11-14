package corvus.interviews.device_readings;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import corvus.interviews.device_readings.device.Device;
import corvus.interviews.device_readings.device.DeviceReading;

@Service
public class DeviceService {

    private final ConcurrentHashMap<String, Device> devices; // Lets us perform safe updates per device

    public DeviceService() {
        devices = new ConcurrentHashMap<String, Device>();
    }

    /**
     * - Adds the device to the records if it is not present already.
     * - Updates the device's records with the new readings.
     * - Would be nice in the future if it returned a device summary or similar to confirm success.
     * @param id the device UUID
     * @param readings array of readings
     */
    public void addReadings(String id, DeviceReading[] readings) {
        final Device device = devices.get(id) == null ? new Device(id) : devices.get(id);
        devices.putIfAbsent(id, device);
        Arrays.asList(readings).forEach( (reading) -> device.addReading(reading));
    }

    /**
     * Retrieves the most recent reading timestamp for a diven device
     * @param deviceId the device UUID
     * @return the most recent timestamp
     */
    public ZonedDateTime getLatestReading(String deviceId) {
        return devices.get(deviceId).getLatestReading().getTimestamp();
    }

    /**
     * Returns the cumulative count for a given device
     * @param deviceId the device UUID
     * @return the cumulative count
     */
    public int getCumulativeCount(String deviceId) {
        return devices.get(deviceId).getCount();
    }

}
