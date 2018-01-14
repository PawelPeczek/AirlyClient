package edu.cs.agh.airly_client.Views;

/**
 * View associated with mode --sensor-id
 */
public class SpecSensorView extends View {
    /**
     * Implementation of abstract method from base class.
     */
    @Override
    public void renderView() {
        if(data.containsKey("SensorDetailsMode") && data.get("SensorDetailsMode").equals(Boolean.TRUE)){
            System.out.println(ANSI_YELLOW +"WARNING!" + ANSI_RESET
                    +"\n--sensor-info doesn't work with --sensor-id (only with --latitude/--longitude)");
        }
    }

    /**
     * Implementation of abstract method from base class.
     * @return Header of SpecSensorView view.
     */
    @Override
    protected String generateHeaderMessage() {
        return "";
    }
}
