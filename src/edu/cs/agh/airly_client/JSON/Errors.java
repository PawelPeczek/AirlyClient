package edu.cs.agh.airly_client.JSON;

import java.util.LinkedList;

/**
 * Simple POJO class that describes Error response from server.
 */
public class Errors {
    private LinkedList<Error> errors;

    public LinkedList<Error> getErrors() {
        return errors;
    }

    public void setErrors(LinkedList<Error> errors) {
        this.errors = errors;
    }
}
