package edu.cs.agh.airly_client.JSON;

/**
 * Simple POJ class that describes Error response from server.
 */
public class SingleErrorMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
