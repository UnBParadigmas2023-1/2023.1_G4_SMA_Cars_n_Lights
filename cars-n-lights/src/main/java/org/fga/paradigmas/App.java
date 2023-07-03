package org.fga.paradigmas;

import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.mocks.CommandersMockData;
import org.fga.paradigmas.mocks.TrafficLightsMockData;
import org.fga.paradigmas.screens.MainScreen;

import jade.core.Agent;
import jade.wrapper.AgentController;

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

        TrafficLightsMockData.getTrafficLights().forEach(trafficLight -> {
            try {
                Object[] args = {trafficLight.getLabel()};
                // Criar o agente secundário dentro do container
                AgentController agentController = getContainerController().createNewAgent(trafficLight.getLabel(), "org.fga.paradigmas.agents.TrafficLightAgent", args);

                // Iniciar o agente secundário
                agentController.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        CommandersMockData.getTrafficLightCommanders().forEach(commander -> {
            try {
                Object[] args = {commander.getLabel()};
                // Criar o agente secundário dentro do container
                AgentController agentController = getContainerController().createNewAgent(commander.getLabel(), "org.fga.paradigmas.agents.TrafficLightCommanderAgent", args);

                // Iniciar o agente secundário
                agentController.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new MainScreen(TITLE, WIDTH, HEIGHT);
    }
}
