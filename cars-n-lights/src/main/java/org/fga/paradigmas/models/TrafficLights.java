package org.fga.paradigmas.models;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class TrafficLights extends Agent {
    
    private static final long serialVersionUID = 7L;

    protected void setup() {

        DFAgentDescription agentDesc = new DFAgentDescription();
        agentDesc.setName(getAID());

        ServiceDescription svcDesc = new ServiceDescription();
        svcDesc.setType("trafficLight");
        svcDesc.setName("trafficLight_" + getAID());

        agentDesc.addServices(svcDesc);

        try {
            DFService.register(this, agentDesc);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        addBehaviour(new TestExampleClass());
    }

    private class TestExampleClass extends CyclicBehaviour {

        private static final long serialVersionUID = 7L;

        public void action () {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE); 
            ACLMessage msg = myAgent.receive(mt);

            if (msg != null) {

                String msgContent = msg.getContent();

                System.out.println("Conteúdo da mensagem recebida: " + msgContent);

                switch (msgContent) {
                    case "turnOff":
                        System.out.println("Turning traffic light off!");
                        break;
                    case "turnOn":
                        System.out.println("Turning traffic light on!");
                        break;
                    case "takeDown":
                        System.out.println("No.");
                        break;
                    default:
                        System.out.println("Choose an option: turnOff or turnOn");
                }

            } else {
                System.out.println("Bye!");
                block();
            }
        
        }

    }

    protected void takeDown () {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        System.out.println("Traffic Light "+getAID().getName()+" is down!");
    }
}