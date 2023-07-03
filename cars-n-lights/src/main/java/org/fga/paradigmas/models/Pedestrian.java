package org.fga.paradigmas.models;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Pedestrian extends Agent {

    private static final long serialVersionUID = 1L;
    // Posição atual do pedestre
    private Node position;

    protected void setup() {

        // Register the book-selling service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("walking-and-stopping");
        sd.setName("JADE-pedestrian");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        }
        catch (FIPAException e) {
            e.printStackTrace();
        }

        // Adicionando behaviour de travessia de faixa
        addBehaviour(new CrossStreet());
    }
    private void walk() {
            // crossWalkOrientation = getCrossWalkOrientation();
            // int Ypos = position.getY();
            // int Xpos = position.getX();
            // if(crossWalkOrientation == "horizontal"){
            //    position.setX(Xpos + 1);
            // }
            // else {
            //    position.setY(Ypos + 1);
            // }
    }

    public class CrossStreet extends OneShotBehaviour {

        private static final long serialVersionUID = 1L;

        public void action() {

            //if(...){
            //   walk();
            //}
        }
    }
    // Put agent clean-up operations here
    protected void takeDown() {
        // Deregister from the yellow pages
        try {
            DFService.deregister(this);
        }
        catch (FIPAException e) {
            e.printStackTrace();
        }

        // Printout a dismissal message
        System.out.println("Pedestrian-agent "+getAID().getName()+" terminating.");
    }
}
