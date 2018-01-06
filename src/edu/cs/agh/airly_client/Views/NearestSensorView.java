package edu.cs.agh.airly_client.Views;

public class NearestSensorView extends View {
    @Override
    public void renderView() {
        if(data.containsKey("SensorData"))
            System.out.println(data.get("SensorData").toString());
    }
}
