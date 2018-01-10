package edu.cs.agh.airly_client;

public class ProcessingException extends Exception {
    public ProcessingException() { super(); }
    public ProcessingException(String message) { super(message); }
    public ProcessingException(String message, Throwable cause) { super(message, cause); }
    public ProcessingException(Throwable cause) { super(cause); }
}
