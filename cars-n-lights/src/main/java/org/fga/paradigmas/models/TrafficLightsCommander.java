package org.fga.paradigmas.models;

import java.util.Hashtable;
import java.util.Set;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class TrafficLightsCommander extends Agent {
    
    private static final long serialVersionUID = 7L;
    private Hashtable<String, Boolean> trafficLightsCatalogue;

    protected void setup() {

        trafficLightsCatalogue = new Hashtable<String, Boolean>();

        DFAgentDescription agentDesc = new DFAgentDescription();
        agentDesc.setName(getAID());

        ServiceDescription svcDesc = new ServiceDescription();
        svcDesc.setType("trafficLight");
        svcDesc.setName("trafficLight_" + getAID());

        agentDesc.addServices(svcDesc);

        this.addTrafficLightsToCatalogue("trafficLight1", false);
        this.addTrafficLightsToCatalogue("trafficLight2", true);

        try {
            DFService.register(this, agentDesc);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        addBehaviour(new UpdateTrafficLightsServer());
    }

    private class UpdateTrafficLightsServer extends CyclicBehaviour {

        private static final long serialVersionUID = 7L;

        public void action () {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM); 
            ACLMessage msg = myAgent.receive(mt);               

            try {
                Thread.sleep(5000);

                Set<String> trafficLightsKeys = trafficLightsCatalogue.keySet();
                

                for (String  element : trafficLightsKeys) {
                    ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);

                    boolean inverseValue = !trafficLightsCatalogue.get(element);

                    message.addReceiver(new AID(element, AID.ISLOCALNAME));
                    message.setContent("" + inverseValue);
                    send(message);

                    trafficLightsCatalogue.put(element, inverseValue);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Bye!");
                block();
            }
        }
    }

    public void addTrafficLightsToCatalogue(final String name, final boolean state) {
        addBehaviour(new OneShotBehaviour() {
           private static final long serialVersionUID = 7L;
           
           public void action () {
            trafficLightsCatalogue.put(name, state);
            System.out.println(name + " added to commander!");
           }


        });
    }

    protected void takeDown () {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        System.out.println("Commander "+getAID().getName()+" is down!");
    }
}