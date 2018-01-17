package edu.cs.agh.airly_client.JSON;

/**
 * Simple POJO class that describes Error response from server.
 */
public class Error {
    private String message;

    private Integer code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
