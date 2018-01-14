package edu.cs.agh.airly_client.RESTClient;

/**
 * Exception class that represents error while
 * working of RESTClient object
 */
public class RESTClientException extends Exception{
    public RESTClientException() { super(); }
    public RESTClientException(String message) { super(message); }
    public RESTClientException(String message, Throwable cause) { super(message, cause); }
    public RESTClientException(Throwable cause) { super(cause); }
}
