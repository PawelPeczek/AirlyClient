package edu.cs.agh.airly_client.RESTClient;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for sending GET requests to server and receiving
 * answers.
 */
public class RESTClient {
    /**
     * APIKey used to authenticate at the server side.
     */
    private final String APIKey;
    /**
     * Base URL to connect.
     */
    private final String URLBase = "https://airapi.airly.eu";

    /**
     * @param APIKey Given APIKey to be used.
     */
    protected RESTClient(String APIKey){
        this.APIKey =  APIKey;
    }

    /**
     * Method responsible for preparing valid HttpURLConnection
     * for request.
     *
     * @param method REST API Method to be used.
     * @param params Parameters of the method
     * @return Valid HttpURLConnection object with all options prepared.
     * @throws IOException when error with connection occurs.
     * @throws InterruptedException when breaking the process while waiting
     * for reconnect to server.
     * @throws RESTClientException when 3-times connection try failed.
     */
    protected HttpURLConnection prepareConnection(APIMethods method, HashMap<String, String> params)
            throws IOException, InterruptedException, RESTClientException {
        return prepareConnection(BuildURL(method, params));
    }

    /**
     * Method responsible for preparing valid HttpURLConnection
     * for request.
     *
     * @param methodAndParams URL prepared to be used (only relative path -
     *                        the part of URL after URLBase!).
     * @return Valid HttpURLConnection object with all options prepared.
     * @throws IOException when error with connection occurs.
     * @throws InterruptedException when breaking the process while waiting
     * for reconnect to server.
     * @throws RESTClientException when 3-times connection try failed.
     */
    protected HttpURLConnection prepareConnection(String methodAndParams)
            throws IOException, InterruptedException, RESTClientException {
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
                  throw new RESTClientException("Couldn't connect to server with 3 tries...", ex);
                }
                counter++;
                Thread.sleep(3000);
            }
        }

        return conn;
    }

    /**
     * Method responsible for building URL from method name and params.
     *
     * @param method Method to be used.
     * @param params Parameters of the method.
     * @return Relative URL (without URLBase).
     * @throws UnsupportedEncodingException when URLEncoder error occurs.
     */
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
