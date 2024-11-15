package corvus.interviews.device_readings;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import corvus.interviews.device_readings.requests.CumulativeCountResponse;
import corvus.interviews.device_readings.requests.DeviceReadingPostRequest;
import corvus.interviews.device_readings.requests.LatestTimestampResponse;

@RestController
public class Controller {

    private final DeviceService deviceService;

    public Controller(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/api/devices/{deviceId}/readings")
    public ResponseEntity<Object> addReadings(@PathVariable String deviceId, @RequestBody DeviceReadingPostRequest requestBody) {
        try {
            if (!deviceId.equals(requestBody.getId())) {
                return new ResponseEntity<>("Device IDs do not match.", HttpStatus.BAD_REQUEST);
            }
            deviceService.addReadings(requestBody.getId(), requestBody.getReadings());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error updating readings for deviceId " + requestBody.getId(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Updated readings for device " + requestBody.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/api/devices/{deviceId}/latest")
    public ResponseEntity<Object> getLatestDeviceReading(@PathVariable String deviceId) {
        try {
            LatestTimestampResponse latestTimestamp = new LatestTimestampResponse(deviceService.getLatestReading(deviceId));
            return new ResponseEntity<>(latestTimestamp, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error fetching latest reading for deviceId " + deviceId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/devices/{deviceId}/count")
    public ResponseEntity<Object> getCumulativeDeviceCount(@PathVariable String deviceId) {
        try {
            CumulativeCountResponse countResponse = new CumulativeCountResponse(deviceService.getCumulativeCount(deviceId));
            System.out.println(countResponse.toString());
            return new ResponseEntity<>(countResponse, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Error fetching cumulative count for deviceId " + deviceId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Sanity check endpoint
    @GetMapping("/api/test")
    public String getString() {
        return "Hello World!";
    }

}
