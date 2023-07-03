package org.fga.paradigmas.agents;

import org.fga.paradigmas.mocks.TrafficLightsMockData;
import org.fga.paradigmas.models.TrafficLight;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class TrafficLightAgent extends Agent {

    private TrafficLight trafficLight;

    public TrafficLightAgent() {}
    
    private static final long serialVersionUID = 7L;
    private static boolean state = false;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        this.trafficLight = TrafficLightsMockData.get((String) args[0]);

        DFAgentDescription agentDesc = new DFAgentDescription();
        agentDesc.setName(getAID());

        ServiceDescription svcDesc = new ServiceDescription();
        svcDesc.setType("trafficLight");
        svcDesc.setName(this.trafficLight.getLabel());

        agentDesc.addServices(svcDesc);

        try {
            DFService.register(this, agentDesc);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        
        addBehaviour(new ManageTrafficLight(this.trafficLight));
    }

    private class ManageTrafficLight extends CyclicBehaviour {

        private TrafficLight trafficLight;
        private static final long serialVersionUID = 7L;
        private MessageTemplate mt;

        public ManageTrafficLight(TrafficLight trafficLight) {
            this.trafficLight = trafficLight;
        }

        @Override
        public void action () {
            ACLMessage msg = myAgent.receive(mt);
            
            if (msg != null) {
                
                if (msg.getPerformative() == ACLMessage.PROPOSE) {
                    String msgContent = msg.getContent();
            
                    switch (msgContent) {
                        case "false":
                            System.out.println("Turning traffic light off!");
                            state = false;
                            this.trafficLight.setState(state);
                            break;

                        case "true":
                            System.out.println("Turning traffic light on!");
                            state = true;
                            this.trafficLight.setState(state);
                            break;

                        default:
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