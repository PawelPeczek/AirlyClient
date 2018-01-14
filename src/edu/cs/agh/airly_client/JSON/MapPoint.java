package edu.cs.agh.airly_client.JSON;


import java.util.LinkedList;

/**
 * POJO Class that represents basic MapPoint data
 * which are possible to receive from server.
 */
public class MapPoint {
    private ExtendedMeasurements currentMeasurements;
    private LinkedList<SensorData> forecast = new LinkedList<>();
    private LinkedList<SensorData> history = new LinkedList<>();

    public ExtendedMeasurements getCurrentMeasurements() {
        return currentMeasurements;
    }

    public void setCurrentMeasurements(ExtendedMeasurements currentMeasurements) {
        this.currentMeasurements = currentMeasurements;
    }

    public LinkedList<SensorData> getForecast() {
        return forecast;
    }

    public void setForecast(LinkedList<SensorData> forecast) {
        this.forecast = forecast;
    }

    public LinkedList<SensorData> getHistory() {
        return history;
    }

    public void setHistory(LinkedList<SensorData> history) {
        this.history = history;
    }
}
