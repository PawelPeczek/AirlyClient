package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.HTTPclient.HTTPClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SensorData;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.ProcessingException;
import edu.cs.agh.airly_client.Views.View;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Model {

    protected HashSet<View> observers = new HashSet<>();

    public void addObserver(HashSet<View> views){
        observers.addAll(views);
    }

    public abstract void processData(ProgramInput input) throws IOException, InterruptedException, HTTPClientException;

    protected void notifyViews(String name, Object data){
        for (View v: observers) {
            v.update(name, data);
        }
    }

    protected boolean isObjectEmpty(SingleSensor obj){
        boolean result = false;
        if(obj == null ||
                (obj.getId() == null && obj.getPollutionLevel() == null &&
                obj.getPm10() == null && obj.getName() == null && obj.getPm25() == null
                && obj.getAirQualityIndex() == null && obj.getVendor() == null)) result = true;
        return result;
    }

    protected boolean isObjectEmpty(MapPoint obj){
        boolean result = false;
        if(obj == null || obj.getCurrentMeasurements() == null ||
                (obj.getCurrentMeasurements().getPm1() == null && obj.getCurrentMeasurements().getPm10() == null
                && obj.getCurrentMeasurements().getPm25() == null
                && obj.getCurrentMeasurements().getPollutionLevel() == null
                && obj.getCurrentMeasurements().getAirQualityIndex() == null)) result = true;
        return result;
    }
}
