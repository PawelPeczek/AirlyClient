package edu.cs.agh.airly_client.Views;

public class SpecSensorView extends View {
    @Override
    public void renderView() {
        if(data.containsKey("MapPointData"))
            System.out.println(data.get("MapPointData").toString());
    }
}
