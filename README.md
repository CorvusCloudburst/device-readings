# Device Readings (Brightwheel)

Take home interview exercise for Brightwheel.

Please excuse the chaos, I haven't written in Java / Spring in over 4 years, but I wanted to give it a go rather than using something like Express.js which wouldn't be as applicable to your stack. I think I did pretty well, all things considered!

## Getting started

You should have Java 17 and Maven installed to run this application.

Clone the repository:
```
git clone git@github.com:CorvusCloudburst/device-readings.git
```

To get the local application running, use the following commands in the project root:

```
mvn clean install
```

```
./mvnw spring-boot:run
```

For a quick and easy sanity check to ensure the application is running as expected, you can try the following api call in another terminal window:
```
curl http://localhost:8080/api/test
```
If you receive "Hello World!" as the response, the server is up and running! Congrats!

## Device Readings API
The endpoints for the device readings API are as follows:

| Endpoint      | Parameters      | Returns |
| ------------- | ------------- | ------------- |
| **POST** `api/devices/{deviceId}/readings` | Expects a body as defined in the requirements. | A string confirming the update. |
| **GET** `api/devices/{deviceId}/latest` | Expects a `deviceId` as a path parameter. | A JSON containing `latest_timestamp` |
| **GET** `api/devices/{deviceId}/count` | Expects a `deviceId` as a path parameter. | A JSON containing `cumulative_count` |

### RESTful API Notes
The path variable wasn't exactly necessary on the POST request, since the ID is included in the body. But since I was going for a REST API I felt a deep urge to do it properly. If the security of the UUIDs are a concern, considerations could be made and a non-RESTful design could be used.

A RESTful design does add easy room for expansion into other functionality, such as the ability for a client to request and modify earlier readings.

### Examples

To test the API, you can start up the app and try some of the following example requests.

**The example POST request from the assignment description:**
```
curl --json '{"id":"36d5658a-6908-479e-887e-a949ec199272","readings":[{"timestamp":"2021-09-29T16:08:15+01:00","count":2},{"timestamp":"2021-09-29T16:09:15+01:00","count":15}]}' http://localhost:8080/api/readings
```

**Fetch the latest timestamp for this device:**
```
curl http://localhost:8080/api/devices/36d5658a-6908-479e-887e-a949ec199272/latest
```

**Fetch the cumulative count for this device:**
```
curl http://localhost:8080/api/devices/36d5658a-6908-479e-887e-a949ec199272/count
```

## Project Structure

It's been awhile since I've worked in Java or Maven, so I admit organized my classes based more on vibes than on any formalized system.

Those familiar with Spring Boot or similarly structured frameworks will recognize the Controller / Service structure. I left these classes in the topmost folder so they would be easy to find, as they do most of the heavy lifting.

The `requests` folder contains object defining the structure of the API requests and responses.

The `device` folder contains objects that I found useful in defining devices and readings.

## Improvements & Optimizations

### Timestamps
Firstly and foremostly I want to note that the timezones are a little wonky. Ideally, I would return the timestamp in the same timezone as it was entered in. Instead, I am returning them in +0.
I ran decently over time setting everything up since I didn't have Java installed on my personal machine, so I decided to let it lie for now. But given more time I would troubleshoot this issue until the timezone tomfoolery is resolved.

We had the same issue in the Donations project I worked on with Walmart, which turned out to be a one line fix. So I know it's likely a very simple fix I just didn't have the bandwidth to spot in the moment. Timezones are ever the enemy of the engineer.

### Error Handling
I started off by adding very basic error handling. It returns a 500 response and a generic error string to the end user, then outputs the exception message in System.out.

Given more time, I would like to add more graceful and detailed error handling to the application. This would include handling such as:
- 404 responses for devices that do not exist
- 400 responses when a necessary field is missing from the post body
- More detailed internal error logging throughout both the Controller and Service to better indicate where failures occur

### Testing
Let's be real, two hours is not nearly enough time to write a fully functional API with tests and all. But given a more extended timeline to do so, I would want to add unit tests for the Service methods, as well as integration tests at the Controller level.

This would be great to do alongside the updated error handling discussed above, to ensure that the correct response codes and error outputs are returning when different types of unacceptable calls are made.
