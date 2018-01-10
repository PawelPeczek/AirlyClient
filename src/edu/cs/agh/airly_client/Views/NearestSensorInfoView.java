package edu.cs.agh.airly_client.Views;

import edu.cs.agh.airly_client.JSON.SingleSensor;

public class NearestSensorInfoView extends View{
    @Override
    public void renderView() {
        System.out.println(generateHeaderMessage());
        System.out.println("See how good the air quality is:\n");
        if (data.containsKey("SensorInfo")){
            SingleSensor nearestSensor = (SingleSensor) (data.get("SensorInfo"));
            System.out.println(generateAsciiArt(nearestSensor.getPollutionLevel()));
            System.out.println(generateInfoAboutSensor(nearestSensor));
            if(data.containsKey("HistoryMode") && data.get("HistoryMode").equals(Boolean.TRUE))
                System.out.println(ANSI_YELLOW +"WARNING!" + ANSI_RESET
                        +"\n--sensor-info doesn't work with --history");
        } else System.out.println(generateNotFoundElementMessage("nearest sensor info"));
    }

    @Override
    protected String generateHeaderMessage() {
        return "\n******************************************************************\n" +
                "*                                                                *\n" +
                "*                        Nearest sensor info                     *\n" +
                "*                                                                *\n" +
                "******************************************************************\n";
    }

    private String generateInfoAboutSensor(SingleSensor nearestSensor){
        return "ID: " + (nearestSensor.getId() != null ? nearestSensor.getId() : "N/A") +
                "\n" +
                "Distance: " +
                (nearestSensor.getDistance() != null ?
                        Math.round(nearestSensor.getDistance()) + "m" : "N/A") +
                "\n" +
                "Location: " +
                prepareLocalizationInfo(nearestSensor.getLocation()) +
                "\n" +
                "Address: " +
                prepareAddressInfo(nearestSensor.getAddress()) +
                "\n" +
                "Vendor: " +
                (nearestSensor.getVendor() != null ? nearestSensor.getVendor() : "N/A") +
                "\n" +
                "Name: " +
                (nearestSensor.getName() != null ? nearestSensor.getName() : "N/A") +
                "\n" +
                generateBasicMeasurInfo(nearestSensor);
    }

}
