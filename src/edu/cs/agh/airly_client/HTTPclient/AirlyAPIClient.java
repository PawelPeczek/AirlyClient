package edu.cs.agh.airly_client.HTTPclient;

import com.google.gson.Gson;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.JSON.SensorData;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class AirlyAPIClient extends HTTPClient {
    private final Gson Gson = new Gson();

    public MapPoint getMeasurementsForMapPoint(Double latitude, Double longitude) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("latitude", latitude.toString());
        params.put("longitude", longitude.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.mapPoint, params);
        MapPoint result = Gson.fromJson(new InputStreamReader(conn.getInputStream()), MapPoint.class);
        System.out.println(result.getCurrentMeasurements().toString());
        for(SensorData sd : result.getHistory()){
            System.out.println(sd.toString());
        }

        for(SensorData sd : result.getForecast()){
            System.out.println(sd.toString());
        }
        conn.disconnect();
        return result;
    }

    public SingleSensor getMeasurementForNearestSensor(Double latitude, Double longitude)
            throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("latitude", latitude.toString());
        params.put("longitude", longitude.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.nearestSensor, params);
        SingleSensor result =
                Gson.fromJson(new InputStreamReader(conn.getInputStream()), SingleSensor.class);
        System.out.println(result);
        conn.disconnect();
        return result;
    }

    public MapPoint getMeasurementsFromSpecificSensor(Integer sensorID) throws IOException {
        HashMap<String, String> params = new HashMap<>();
        params.put("sensorId", sensorID.toString());
        HttpURLConnection conn = prepareConnection(APIMethods.sensorMeasurments, params);
        MapPoint result = Gson.fromJson(new InputStreamReader(conn.getInputStream()), MapPoint.class);
        System.out.println(result.getCurrentMeasurements().toString());
        for(SensorData sd : result.getHistory()){
            System.out.println(sd.toString());
        }

        for(SensorData sd : result.getForecast()){
            System.out.println(sd.toString());
        }
        conn.disconnect();
        return result;
    }

}
