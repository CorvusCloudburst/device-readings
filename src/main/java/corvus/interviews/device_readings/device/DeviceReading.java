package corvus.interviews.device_readings.device;

import java.time.ZonedDateTime;

public class DeviceReading implements Comparable<DeviceReading> {

    // ------------- Properties -------------
    private ZonedDateTime timestamp;
    private int count;

    public DeviceReading(ZonedDateTime timestamp, int count) {
        this.timestamp = timestamp;
        this.count = count;
    }

    // ------------- Getters / Setters -------------
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // ------------- Helps with sorting -------------
    @Override
    public int compareTo(DeviceReading other) {
        if (other.getTimestamp().isAfter(timestamp)) {
            return -1;
        } else if (other.getTimestamp().isBefore(timestamp)) {
            return 1;
        } else {
            return 0;
        }
    }

    // ------------- Helps with testing in local -------------
    @Override
    public String toString() {
        return "DeviceReading: "
            + "\n\tTimestamp: " + timestamp.toString()
            + "\n\tCount: " + count;
    }
}
