package edu.cs.agh.airly_client.Model;

import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.View;

import java.io.IOException;
import java.util.HashSet;

/**
 * Abstract class that represent basic Model.
 */
public abstract class Model {

    /**
     * Views which observes specific model.
     */
    protected HashSet<View> observers = new HashSet<>();

    /**
     * Method that allows to add some view as an observer of current model.
     *
     * @param views Views that has to be add to observers HashSet.
     */
    public void addObserver(HashSet<View> views){
        observers.addAll(views);
    }

    /**
     * Abstract method which should be implemented as a core of model
     * data-processing method.
     *
     * @param input Parsed input from command-line.
     * @throws IOException when error with server connection occurs.
     * @throws InterruptedException when somewhat process is killed while
     * trying to reconnect to server.
     * @throws RESTClientException when inner RESTClient error occurs.
     */
    public abstract void processData(ProgramInput input) throws IOException, InterruptedException, RESTClientException;

    /**
     * Method responsible for notifying observers about changes in state of model object.
     *
     * @param name Name of data that is produced by model.
     * @param data Actual data object.
     */
    protected void notifyViews(String name, Object data){
        for (View v: observers) {
            v.update(name, data);
        }
    }

    /**
     * Method checks whether SingleSensor contains data to show the user.
     *
     * @param obj Single sensor to check.
     * @return Check status.
     */
    protected boolean isObjectEmpty(SingleSensor obj){
        boolean result = false;
        if(obj == null ||
                (obj.getId() == null && obj.getPm10() == null && obj.getName() == null && obj.getPm25() == null
                && obj.getAirQualityIndex() == null && obj.getVendor() == null)) result = true;
        return result;
    }

    /**
     * Method checks whether MapPoint contains data to show the user.
     *
     * @param obj MapPoint to check.
     * @return Check status.
     */
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
