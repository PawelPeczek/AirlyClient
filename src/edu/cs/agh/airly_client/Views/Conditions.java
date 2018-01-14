package edu.cs.agh.airly_client.Views;


/**
 * Enum maps smog Conditions into ASCII-arts URI with "images" appropriate
 * to Condition
 */
public enum Conditions {
    OK("/edu/cs/agh/airly_client/ASCII_arts/good.txt"),
    QuiteOK("/edu/cs/agh/airly_client/ASCII_arts/quite_good.txt"),
    Average("/edu/cs/agh/airly_client/ASCII_arts/average.txt"),
    Bad("/edu/cs/agh/airly_client/ASCII_arts/worst_condition.txt"),
    Fatal("/edu/cs/agh/airly_client/ASCII_arts/even_worst.txt");
    /**
     * Address to resource in package
     */
    public final String URI;

    /**
     * Private constructor that allows to provide this.URI value
     * for each element of Conditions.
     *
     * @param URI Address to resource
     */
    private Conditions(String URI){
        this.URI = URI;
    }
}
