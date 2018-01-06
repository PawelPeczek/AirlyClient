package edu.cs.agh.airly_client.Views;

public class NearestSensorInfoView extends View{
    @Override
    public void renderView() {
        if(data.containsKey("SensorInfo"))
            System.out.println(data.get("SensorInfo").toString());
    }
}
