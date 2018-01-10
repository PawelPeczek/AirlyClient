package edu.cs.agh.airly_client.HTTPclient;

public class HTTPClientException extends Exception{
    public HTTPClientException() { super(); }
    public HTTPClientException(String message) { super(message); }
    public HTTPClientException(String message, Throwable cause) { super(message, cause); }
    public HTTPClientException(Throwable cause) { super(cause); }
}
