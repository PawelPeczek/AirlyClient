package edu.cs.agh.airly_client.JSON;

/**
 * POJO Class that represents basic SensorData data
 * which are possible to receive from server.
 */
public class SensorData {
    private ExtendedMeasurements measurements;

    private String tillDateTime;

    private String fromDateTime;

    public ExtendedMeasurements getMeasurements ()
    {
        return measurements;
    }

    public void setMeasurements (ExtendedMeasurements measurements)
    {
        this.measurements = measurements;
    }

    public String getTillDateTime ()
    {
        return tillDateTime;
    }

    public void setTillDateTime (String tillDateTime)
    {
        this.tillDateTime = tillDateTime;
    }

    public String getFromDateTime ()
    {
        return fromDateTime;
    }

    public void setFromDateTime (String fromDateTime)
    {
        this.fromDateTime = fromDateTime;
    }

    @Override
    public String toString()
    {
        return "[measurements = "+measurements+", tillDateTime = "+tillDateTime+", fromDateTime = "+fromDateTime+"]";
    }
}
