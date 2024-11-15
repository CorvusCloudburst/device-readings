package corvus.interviews.device_readings.requests;

public class CumulativeCountResponse {

    int cumulative_count;

    public CumulativeCountResponse(int count) {
        this.cumulative_count = count;
    }

    public int getCumulative_count() {
        return cumulative_count;
    }
}
