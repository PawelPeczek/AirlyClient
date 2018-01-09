package edu.cs.agh.airly_client.Views;


public enum Conditions {
    OK("./src/edu/cs/agh/airly_client/ASCII_arts/good.txt"),
    QuiteOK("./src/edu/cs/agh/airly_client/ASCII_arts/quite_good.txt"),
    Average("./src/edu/cs/agh/airly_client/ASCII_arts/average.txt"),
    Bad("./src/edu/cs/agh/airly_client/ASCII_arts/worst_condition.txt"),
    Fatal("./src/edu/cs/agh/airly_client/ASCII_arts/even_worst.txt");
    public final String path;
    private Conditions(String path){
        this.path = path;
    }
}
