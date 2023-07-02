package org.fga.paradigmas.models;

import jade.core.AID;
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
    private static boolean state = false;

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
        private MessageTemplate mt;

        public void action () {
            ACLMessage msg = myAgent.receive(mt);
            
            if (msg != null) {
                
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    String msgContent = msg.getContent();
            
                    switch (msgContent) {
                        case "false":
                            System.out.println("Turning traffic light off!");
                            state = false;
                            break;

                        case "true":
                            System.out.println("Turning traffic light on!");
                            state = true;
                            break;

                        default:
                            System.out.println("Invalid option!");
                    }
    
                }
                
                if (msg.getPerformative() == ACLMessage.REQUEST) {
                    ACLMessage reply = msg.createReply();

                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("" + state);
                    
                    System.out.println(reply);
                    send(reply);
                }
                
            } else {
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