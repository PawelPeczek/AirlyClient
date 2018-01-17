package edu.cs.agh.airly_client.Controller;

import edu.cs.agh.airly_client.RESTClient.RESTClientException;
import edu.cs.agh.airly_client.Model.Model;
import edu.cs.agh.airly_client.Parser.ProgramInput;
import edu.cs.agh.airly_client.Views.View;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.ExecutionException;


/**
 * Abstract controller base class.
 */
public abstract class Controller {
    /**
     * Model associated to controller (in this implementation only one possible)
     */
    protected Model model;
    /**
     * Views associated to controller.
     */
    protected HashSet<View> views = new LinkedHashSet<>();

    /**
     * Method that is responsible for main controller action
     * (here its default implementation).
     *
     * @param input Parsed user input.
     * @throws IOException when IO error with file or server connection
     * occurs.
     * @throws InterruptedException when program is terminated while
     * trying to reconnect to server.
     * @throws RESTClientException when internal RESTClient error occurs.
     * @throws ExecutionException when parallel execution error occurs.
     */
    public void mainAction(ProgramInput input)
            throws InterruptedException, RESTClientException, IOException,
            ExecutionException {
        model.processData(input);
        for (View view : views) {
            view.renderView();
        }
    }
}
