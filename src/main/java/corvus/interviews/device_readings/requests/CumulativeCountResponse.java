package corvus.interviews.device_readings.requests;

public class CumulativeCountResponse {

    int cumulative_count;

    public CumulativeCountResponse(int count) {
        this.cumulative_count = count;
    }

    public int getCumulativeCount() {
        return cumulative_count;
    }
}
