package edu.cs.agh.airly_client.Views;

/**
 * View responsible for showing help page.
 */
public class HelpView extends View {
    /**
     * Implementation of abstract method from base class.
     * Method checks if appropriate view element is present in data HashMap
     * and if so - it's printed. Otherwise appropriate action takes place.
     */
    @Override
    public void renderView() {
        if(data.containsKey("HelpContent"))
            System.out.println(data.get("HelpContent").toString());
    }

    /**
     * Implementation of abstract method from base class.
     * @return HelpView header.
     */
    @Override
    protected String generateHeaderMessage() {
        return "";
    }
}
