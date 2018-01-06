package edu.cs.agh.airly_client.HTTPclient;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {
    private final String APIKey =  "2d35d097f0cf4839a9df2d79fcba632c";
    private final String URLBase = "https://airapi.airly.eu";

//    public void ExampleRequest() throws IOException, URISyntaxException {
//        HashMap<String, String> params = new HashMap<>();
//        params.put("latitude", "50.06");
//        params.put("longitude", "19.93");
//        System.out.println(BuildURL("/v1/mapPoint/measurements", params));
//        URL url = new URL(BuildURL("/v1/mapPoint/measurements", params));
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("apikey", APIKey);
//        Gson g = new Gson();
//        MapPoint mp = g.fromJson(new InputStreamReader(conn.getInputStream()), MapPoint.class);
//        System.out.println(mp.getCurrentMeasurements().toString());
//        for(SensorData sd : mp.getHistory()){
//            System.out.println(sd.toString());
//        }
//
//        for(SensorData sd : mp.getForecast()){
//            System.out.println(sd.toString());
//        }
////        BufferedReader in = new BufferedReader(
////                new InputStreamReader(conn.getInputStream()));
////        String inputLine;
////        StringBuffer content = new StringBuffer();
////        while ((inputLine = in.readLine()) != null) {
////            content.append(inputLine);
////        }
////        System.out.println(content);
////        in.close();
//        conn.disconnect();
//    }

    protected HttpURLConnection prepareConnection(APIMethods method, HashMap<String, String> params)
            throws IOException {
        return prepareConnection(BuildURL(method, params));
    }

    protected HttpURLConnection prepareConnection(String methodAndParams) throws IOException {
        URL url = new URL(URLBase + methodAndParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("apikey", APIKey);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.connect();
        return conn;
    }

    private String BuildURL(APIMethods method, HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        result.append(method.methodName);
        result.append("?");
        for(Map.Entry<String, String> entry : params.entrySet()){
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
