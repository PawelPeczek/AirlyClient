package edu.cs.agh.airly_client.RESTClient;

import com.google.gson.Gson;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SingleSensor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * Object that wraps all AirlyAPI functions possible to use
 * in this application.
 */
public class AirlyAPIClient extends RESTClient {

    /**
     * Object from library that is responsible for parsing JSON to objects.
     */
    private final Gson Gson = new Gson();

    /**
     * @param APIKey APIKey to be used in connection.
     */
    public AirlyAPIClient(String APIKey) {
        super(APIKey);
    }

    /**
     * Method that provide internal application interface to method
     * https://airapi.airly.eu/v1/mapPoint/measurements
     * It maps the JSON server response into object thanks to gson lib.
     * It also handles connection (opening and closing).
     *
     * @param latitude Latitude parameter
     * @param longitude Longitude parameter
     * @return MapPoint object which is just mapped JSON response
     * @throws IOException when error with connection occurs.
     * @throws InterruptedException when breaking the process while waiting
     * for reconnect to server.
     * @throws RESTClientException when 3-times connection try failed.
     */
    public MapPoint getMeasurementsForMapPoint(Double latitude, Double longitude)
            throws IOException, RESTClientException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put("latitude", latitude.toString());
        params.put("longitude", longitude.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.mapPoint, params);
        checkConnectionErrors(conn);
        String content = obtainJasonFromServerResponse(conn);
        MapPoint result = Gson.fromJson(content, MapPoint.class);
        conn.disconnect();
        return result;
    }

    /**
     * Method that provide internal application interface to method
     * https://airapi.airly.eu/v1/nearestSensor/measurements
     * It maps the JSON server response into object thanks to gson lib.
     * It also handles connection (opening and closing).
     *
     * @param latitude Latitude parameter
     * @param longitude Longitude parameter
     * @return Single object which is just mapped JSON response
     * @throws IOException when error with connection occurs.
     * @throws InterruptedException when breaking the process while waiting
     * for reconnect to server.
     * @throws RESTClientException when 3-times connection try failed.
     */
    public SingleSensor getMeasurementForNearestSensor(Double latitude, Double longitude)
            throws IOException, RESTClientException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put("latitude", latitude.toString());
        params.put("longitude", longitude.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.nearestSensor, params);
        checkConnectionErrors(conn);
        String content = obtainJasonFromServerResponse(conn);
        SingleSensor result = Gson.fromJson(content, SingleSensor.class);
        conn.disconnect();
        return result;
    }

    /**
     * Method that provide internal application interface to method
     * https://airapi.airly.eu/v1/sensor/measurements
     * It maps the JSON server response into object thanks to gson lib.
     * It also handles connection (opening and closing).
     *
     * @param sensorID SensorId of requested sensor.
     * @return Single object which is just mapped JSON response
     * @throws IOException when error with connection occurs.
     * @throws InterruptedException when breaking the process while waiting
     * for reconnect to server.
     * @throws RESTClientException when 3-times connection try failed.
     */
    public MapPoint getMeasurementsFromSpecificSensor(Integer sensorID)
            throws IOException, RESTClientException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put("sensorId", sensorID.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.sensorMeasurements, params);
        checkConnectionErrors(conn);
        String content = obtainJasonFromServerResponse(conn);
        MapPoint result = Gson.fromJson(content, MapPoint.class);
        conn.disconnect();
        return result;
    }

    /**
     * Method that handles with connection errors and wraps it in
     * RESTClientException with appropriate comments.
     *
     * @param conn Connection (opened) object.
     * @throws IOException when errors with connection occurs.
     * @throws RESTClientException when not ResponseCode == 200
     */
    private void checkConnectionErrors(HttpURLConnection conn) throws IOException, RESTClientException {
        if(conn.getResponseCode() != 200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getErrorStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            if(responseCode == 500)
                throw new RESTClientException("Unexpected error at server-side. Try again later." +
                        "Server message: " + content.toString());
            if(responseCode == 401 || responseCode == 403)
                throw new RESTClientException("Problem with api-key authorising! Server message: " +
                        content.toString());
            throw new RESTClientException("Serious error occurred. Please contact with the author. " +
                    "Error code 12-090, Server message: " + content.toString());
        }
    }

    /**
     * Method that is responsible for obtaining plain text from server JSON response.
     * @param conn connection (opened) object.
     * @return JSON obtained from server.
     * @throws IOException when errors with connection occurs.
     */
    private String obtainJasonFromServerResponse(HttpURLConnection conn) throws IOException {
        InputStream input = conn.getInputStream();
        if("gzip".equals(conn.getContentEncoding())){
            input = new GZIPInputStream(input);
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(input, "UTF-8"));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
