package edu.cs.agh.airly_client.Parser;

/**
 * Exception that describes error while argument parsing.
 */
public class ArgumentsParserException extends Exception {
    public ArgumentsParserException() { super(); }
    public ArgumentsParserException(String message) { super(message); }
    public ArgumentsParserException(String message, Throwable cause) { super(message, cause); }
    public ArgumentsParserException(Throwable cause) { super(cause); }
    @Override
    public String toString() {
        return getMessage() != null ? getMessage() : "No error message provided.";
    }
}
