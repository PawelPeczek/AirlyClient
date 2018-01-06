package edu.cs.agh.airly_client.Views;

public class HelpView extends View {
    @Override
    public void renderView() {
        if(data.containsKey("HelpContent"))
            System.out.println(data.get("HelpContent").toString());
    }
}
