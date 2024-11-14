package corvus.interviews.device_readings.requests;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LatestTimestampResponse {

    public String latest_timestamp;

    public LatestTimestampResponse(ZonedDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm:ssZ");
        latest_timestamp = date.format(formatter);
    }

    public String getLatest_timestamp() {
        return latest_timestamp;
    }
}
