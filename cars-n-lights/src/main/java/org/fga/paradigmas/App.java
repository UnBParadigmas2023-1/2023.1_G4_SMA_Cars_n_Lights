package org.fga.paradigmas;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
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
        System.out.println("Main-Container Id: " + getAID().getName());
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

        addBehaviour(new ReceberMensagemBehaviour());
    }

    private class ReceberMensagemBehaviour extends CyclicBehaviour {
        public void action() {
            ACLMessage mensagem = myAgent.receive();

            System.out.println("Aguardando alguma mensagem...");

            if (mensagem != null) {
                System.out.println("Mensagem recebida: " + mensagem.getContent());

                // Processar a mensagem recebida
                if (mensagem.getContent().contains("Agente morreu")) {
                    String carLabel = mensagem.getContent().split(": ")[1];

                    try {
                        Object[] args = {carLabel};
                        // Criar o agente secundário dentro do container
                        AgentController agentController = getContainerController().createNewAgent("carAgent" + carLabel, "org.fga.paradigmas.agents.CarAgent", args);

                        // Iniciar o agente secundário
                        agentController.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // Encerrar o comportamento após processar a mensagem, se necessário
                // myAgent.removeBehaviour(this);
            } else {
                block();
            }
        }
    }

}
