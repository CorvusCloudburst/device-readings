package corvus.interviews.device_readings.device;

import java.util.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeSet;

public class Device {

    // ------------- Properties -------------
    private String id;
    private TreeSet<DeviceReading> readings;

    public Device(String id) {
        this.id = id;
        this.readings = new TreeSet<DeviceReading>();
    }

    // ------------- Getters / Setters -------------
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // ------------- Slightly more nuanced functions -------------
    /**
     * Takes an individual reading and adds it to the Set
     * @param reading An individual reading to be added to the records
     */
    public void addReading(DeviceReading reading) {
        readings.add(reading); // Using Set only adds if it's not there already--in case of duplicates.
    }

    /**
     * @return The most recent reading
     */
    public DeviceReading getLatestReading() {
        return readings.last(); // TreeSet lets us grab this without much fanfare.
    }

    /**
     * Returns the sum of all stored counts.
     * @return cumulative reading count
     */
    public int getCount() {
        // Depending on how strict you want to be about OOO structure,
        // this logic could be moved into the Service.
        return readings.stream().mapToInt((reading) -> reading.getCount()).sum();
    }

    // Useful for troubleshooting
    @Override
    public String toString() {
        return "Device{"
            + "\n\tID: " + id
            + "\n\tCountL " + getCount()
            + "\n}";
    }

}
