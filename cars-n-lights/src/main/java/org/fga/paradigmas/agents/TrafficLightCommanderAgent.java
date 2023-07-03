package org.fga.paradigmas.agents;

import java.util.Hashtable;
import java.util.Set;

import org.fga.paradigmas.mocks.CommandersMockData;
import org.fga.paradigmas.models.TrafficLightCommander;
import org.fga.paradigmas.utils.Utils;

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

public class TrafficLightCommanderAgent extends Agent {

    private TrafficLightCommander commander;
    
    private static final long serialVersionUID = 7L;
    private Hashtable<String, Boolean> trafficLightsCatalogue;

    public TrafficLightCommanderAgent() {}

    @Override
    protected void setup() {
        Object[] args = getArguments();

        trafficLightsCatalogue = new Hashtable<String, Boolean>();

        this.commander = CommandersMockData.get((String) args[0]);

        DFAgentDescription agentDesc = new DFAgentDescription();
        agentDesc.setName(getAID());

        ServiceDescription svcDesc = new ServiceDescription();
        svcDesc.setType("trafficLight");
        svcDesc.setName("trafficLight_" + getAID());

        agentDesc.addServices(svcDesc);

        this.commander.getTrafficLights().forEach(trafficLight -> {
            this.addTrafficLightsToCatalogue(trafficLight.getLabel(), trafficLight.getState());
        });

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
                Utils.sleep(2000);

                Set<String> trafficLightsKeys = trafficLightsCatalogue.keySet();
                

                for (String  element : trafficLightsKeys) {
                    ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);

                    boolean inverseValue = !trafficLightsCatalogue.get(element);

                    message.addReceiver(new AID(element, AID.ISLOCALNAME));
                    message.setContent("" + inverseValue);
                    send(message);

                    trafficLightsCatalogue.put(element, inverseValue);
                }
                
            } catch (Exception e) {
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