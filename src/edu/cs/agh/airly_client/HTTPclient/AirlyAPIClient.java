package edu.cs.agh.airly_client.HTTPclient;

import com.google.gson.Gson;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.JSON.SensorData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class AirlyAPIClient extends HTTPClient {

    private final Gson Gson = new Gson();

    public AirlyAPIClient(String APIKey) {
        super(APIKey);
    }

    public MapPoint getMeasurementsForMapPoint(Double latitude, Double longitude)
            throws IOException, HTTPClientException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put("latitude", latitude.toString());
        params.put("longitude", longitude.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.mapPoint, params);
        checkConnectionErrors(conn);
        System.out.println("[DEBUG Server Code] " + conn.getResponseCode());
        String content = obtainJasonFromServerResponse(conn);
        System.out.println("[DEBUG Recieved JSON] " + content);
        MapPoint result = Gson.fromJson(content, MapPoint.class);
        //MapPoint result = Gson.fromJson(new InputStreamReader(conn.getInputStream()), MapPoint.class);
        conn.disconnect();
        return result;
    }

    public SingleSensor getMeasurementForNearestSensor(Double latitude, Double longitude)
            throws IOException, HTTPClientException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put("latitude", latitude.toString());
        params.put("longitude", longitude.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.nearestSensor, params);
        checkConnectionErrors(conn);
        System.out.println("[DEBUG Server Code] " + conn.getResponseCode());
        String content = obtainJasonFromServerResponse(conn);
        System.out.println("[DEBUG Recieved JSON] " + content);
        SingleSensor result = Gson.fromJson(content, SingleSensor.class);
//        SingleSensor result =
//                Gson.fromJson(new InputStreamReader(conn.getInputStream()), SingleSensor.class);
        conn.disconnect();
        return result;
    }

    public MapPoint getMeasurementsFromSpecificSensor(Integer sensorID)
            throws IOException, HTTPClientException, InterruptedException {
        HashMap<String, String> params = new HashMap<>();
        params.put("sensorId", sensorID.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.sensorMeasurements, params);
        checkConnectionErrors(conn);
        System.out.println("[DEBUG Server Code] " + conn.getResponseCode());
        String content = obtainJasonFromServerResponse(conn);
        //MapPoint result = Gson.fromJson(new InputStreamReader(conn.getInputStream()), MapPoint.class);
        System.out.println("[DEBUG Recieved JSON] " + content);
        MapPoint result = Gson.fromJson(content, MapPoint.class);
        conn.disconnect();
        return result;
    }

    private void checkConnectionErrors(HttpURLConnection conn) throws IOException, HTTPClientException {
        if(conn.getResponseCode() != 200){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getErrorStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            if(conn.getResponseCode() == 500)
                throw new HTTPClientException("Unexpected error at server-side. Try again later." +
                        "Server message: " + content.toString());
            if(conn.getResponseCode() == 401 || conn.getResponseCode() == 403)
                throw new HTTPClientException("Problem with api-key authorising! Server message: " +
                        content.toString());
            throw new HTTPClientException("Serious error occurred. Please contact with the author. " +
                    "Error code 12-090, Server message: " + content.toString());
        }
    }

    private String obtainJasonFromServerResponse(HttpURLConnection conn) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
