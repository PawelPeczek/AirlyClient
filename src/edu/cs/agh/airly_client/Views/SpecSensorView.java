package edu.cs.agh.airly_client.Views;

public class SpecSensorView extends View {
    @Override
    public void renderView() {
        if(data.containsKey("SensorDetailsMode") && data.get("SensorDetailsMode").equals(Boolean.TRUE)){
            System.out.println(ANSI_YELLOW +"WARNING!" + ANSI_RESET
                    +"\n--sensor-info doesn't work with --sensor-id (only with --latitude/--longitude)");
        }
    }

    @Override
    protected String generateHeaderMessage() {
        return "";
    }
}
