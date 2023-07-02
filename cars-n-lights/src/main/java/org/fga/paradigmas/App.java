package org.fga.paradigmas;

import jade.core.Agent;
import org.fga.paradigmas.screens.MainScreen;

/**
 * Hello world!
 *
 */
public class App extends Agent {

    private static final String TITLE = "Controle de tráfego";
    private static final Integer WIDTH = 900;
    private static final Integer HEIGHT = 860;

    @Override
    protected void setup() {
        new MainScreen(TITLE, WIDTH, HEIGHT);
    }
}
