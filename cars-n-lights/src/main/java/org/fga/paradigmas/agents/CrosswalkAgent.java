package org.fga.paradigmas.agents;

import org.fga.paradigmas.mocks.CrosswalkMockData;
import org.fga.paradigmas.models.Crosswalk;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.MessageTemplate;

public class CrosswalkAgent extends Agent {

    private Crosswalk crosswalk;
    
    public CrosswalkAgent() {}

    private static final long serialVersionUID = 1L;
    private static boolean state = false;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        this.crosswalk = CrosswalkMockData.get((String) args[0]);

        DFAgentDescription agentDesc = new DFAgentDescription();
        agentDesc.setName(getAID());

        ServiceDescription svcDesc = new ServiceDescription();
        svcDesc.setType("crosswalk");
        svcDesc.setName(this.crosswalk.getLabel());

        agentDesc.addServices(svcDesc);

        try {
            DFService.register(this, agentDesc);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        
        addBehaviour(new ManageCrosswalk(this.crosswalk));
    }

    private class ManageCrosswalk extends CyclicBehaviour {

        private Crosswalk crosswalk;
        private static final long serialVersionUID = 1L;
        private MessageTemplate mt;

        public ManageCrosswalk(Crosswalk crosswalk) {
            this.crosswalk = crosswalk;
        }

        @Override
        public void action () {
            
            this.crosswalk.setState(!this.crosswalk.getTrafficLight().getState());

            state = !this.crosswalk.getTrafficLight().getState();
            
            System.out.println("Estado da faixa: " + state);

        }
    }

    protected void takeDown () {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }

        System.out.println("Crosswalk "+getAID().getName()+" is down!");
    }
}

