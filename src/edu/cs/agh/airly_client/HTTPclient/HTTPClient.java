package edu.cs.agh.airly_client.HTTPclient;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {
    private final String APIKey;
    private final String URLBase = "https://airapi.airly.eu";

    public HTTPClient(String APIKey){
        // for now only
        System.out.println("API: " + APIKey);
        this.APIKey =  APIKey;
    }

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
