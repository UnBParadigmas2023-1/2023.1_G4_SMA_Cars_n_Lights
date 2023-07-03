package org.fga.paradigmas.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class CrosswalkAgent extends Agent {

    private boolean isSemaphoreOpen = false;

    @Override
    protected void setup() {
        System.out.println("Crosswalk Agent");

        addBehaviour(new CrosswalkBehaviour());
    }

    private class CrosswalkBehaviour extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = receive();

            if (msg != null) {
                String content = msg.getContent();

                if (msg.getConversationId().equals("semaphore-status")) {
                    // Atualiza o estado do semáforo
                    isSemaphoreOpen = content.equals("open");
                    System.out.println("Crosswalk: Semaphore status updated. Open: " + isSemaphoreOpen);
                } else if (msg.getConversationId().equals("pedestrian-request")) {
                    // Verifica se o semáforo está aberto e envia uma resposta ao pedestre
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);

                    if (isSemaphoreOpen) {
                        reply.setContent("You can cross the crosswalk");
                    } else {
                        reply.setContent("Wait for the semaphore to open");
                    }

                    send(reply);
                }
            } else {
                block();
            }
        }
    }
}

