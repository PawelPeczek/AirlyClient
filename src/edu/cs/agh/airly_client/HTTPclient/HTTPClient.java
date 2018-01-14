package edu.cs.agh.airly_client.HTTPclient;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {
    private final String APIKey;
    private final String URLBase = "https://airapi.airly.eu";

    public HTTPClient(String APIKey){
        this.APIKey =  APIKey;
    }

    protected HttpURLConnection prepareConnection(APIMethods method, HashMap<String, String> params)
            throws IOException, InterruptedException, HTTPClientException {
        return prepareConnection(BuildURL(method, params));
    }

    protected HttpURLConnection prepareConnection(String methodAndParams)
            throws IOException, InterruptedException, HTTPClientException {
        System.out.println("[DEBUG URL] " + URLBase + methodAndParams);
        System.out.println("[DEBUG API] " + APIKey);
        URL url = new URL(URLBase + methodAndParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("apikey", APIKey);
        int counter = 0;
        while(counter < 3){
            try {
                System.out.println("New try...");
                conn.connect();
                counter = 3;
            } catch (UnknownHostException ex){
                if(counter == 2){
                  throw new HTTPClientException("Couldn't connect to server with 3 tries...", ex);
                }
                counter++;
                Thread.sleep(3000);
            }
        }

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
