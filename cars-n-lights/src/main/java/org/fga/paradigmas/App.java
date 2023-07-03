package org.fga.paradigmas;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import org.fga.paradigmas.mocks.CarsMockData;
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
        CarsMockData.getCars().forEach(car -> {
            try {
                Object[] args = {car.getLabel()};
                // Criar o agente secundário dentro do container
                AgentController agentController = getContainerController().createNewAgent("carAgent" + car.getLabel(), "org.fga.paradigmas.agents.CarAgent", args);

                // Iniciar o agente secundário
                agentController.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new MainScreen(TITLE, WIDTH, HEIGHT);
    }
}
