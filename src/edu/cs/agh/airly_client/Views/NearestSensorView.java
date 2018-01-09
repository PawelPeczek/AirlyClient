package edu.cs.agh.airly_client.Views;

import edu.cs.agh.airly_client.JSON.ExtendedMeasurements;
import edu.cs.agh.airly_client.JSON.MapPoint;
import edu.cs.agh.airly_client.JSON.SingleSensor;
import javafx.css.Match;

public class NearestSensorView extends View {
    @Override
    public void renderView() {
        if(data.containsKey("SensorData"))
            System.out.println(generateHeaderMessage());
            if(data.containsKey("SensorData")){
                MapPoint sensor = (MapPoint) data.get("SensorData");
                System.out.println(sensor.getCurrentMeasurements().toString());
                System.out.println(generateAsciiArt(sensor.getCurrentMeasurements() != null ?
                        sensor.getCurrentMeasurements().getPollutionLevel() : null));
                System.out.println(generateInfoAboutSensor(sensor));
            } else System.out.println(generateNotFoundElementMessage("nearest sensor detailed measurements"));
    }

    @Override
    protected String generateHeaderMessage() {
        return "\n***************************************************\n" +
                "*                                                 *\n" +
                "*           Nearest sensor measurements           *\n" +
                "*                                                 *\n" +
                "***************************************************\n";
    }

    private String generateInfoAboutSensor(MapPoint nearestSensor){
        StringBuilder result = new StringBuilder();
        ExtendedMeasurements currMeasur = nearestSensor.getCurrentMeasurements();
        result.append("Current measurements:\n");
        if(currMeasur != null){
            result.append("Wind speed: ");
            result.append(currMeasur.getWindSpeed() != null ?
                    Math.round(currMeasur.getWindSpeed()) + "mps" : "N/A");
            result.append("\n");
            result.append("Humidity: ");
            result.append(currMeasur.getHumidity() != null ?
                    Math.round(currMeasur.getHumidity()) + "%" : "N/A");
            result.append("\n");
            result.append("Pressure: ");
            result.append(currMeasur.getPressure() != null ?
                    Math.round(currMeasur.getPressure() / 100) + "hPa" : "N/A");
            result.append("\n");
            result.append("Temperature: ");
            result.append(currMeasur.getTemperature() != null ?
                    Math.round(currMeasur.getTemperature()) + "°C" : "N/A");
            result.append("\n");
            result.append(generateBasicMeasurInfo(currMeasur));
            result.append("\n");
            result.append("PM1: ");
            result.append(currMeasur.getPm1() != null ?
                    mapPMToColor(currMeasur.getPm1()) + Math.round(currMeasur.getPm1()) + ANSI_RESET : "N/A");
        } else result.append("Not given for nearest station :(");
        result.append("\n");
        return result.toString();
    }
}
