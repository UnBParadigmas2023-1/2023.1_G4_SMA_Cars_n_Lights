package org.fga.paradigmas.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.mocks.NodesMockData;
import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.CarDirection;
import org.fga.paradigmas.models.Node;
import org.fga.paradigmas.utils.Utils;

public class CarAgent extends Agent {

    private Car car;
    private String label;

    public CarAgent() {}

    @Override
    protected void setup() {
        Object[] args = getArguments();
        String label = (String) args[0];
        this.car = CarsMockData.get(label);

        System.out.println("Car Agent");
        System.out.println("MockData: " + CarsMockData.get((String) args[0]).toString());

        addBehaviour(new MoveCarBehaviour(car));
    }

    private class MoveCarBehaviour extends Behaviour {

        private Car car;
        private Node node = null;

        public MoveCarBehaviour(Car car) {
            this.car = car;
        }

        @Override
        public void action() {
            // Atualiza a posição do carro
            int carX = car.getX();
            int carY = car.getY();

            System.out.println("Car(x,y) = (" + carX + ", " + carY + ")");
            Node node = NodesMockData.getNodeByCoordinates(carX, carY);

            if (node != null) {
                if (this.node == null || (!this.node.equals(node))) {
                    this.node = node;
                }
            }

            if (carX == this.node.getX() && carY == this.node.getY()) {
                CarDirection direction = this.node.getCarRandomDirection(car.getCarDirection());

                car.updateCarPosition(car.getSpeed(), direction);
                System.out.println("Carro: " + car.getLabel() + " indo para: " + direction);
            } else {
                car.updateCarPosition(car.getSpeed(), CarDirection.KEEP);
            }

            Utils.sleep(100);
        }

        @Override
        public boolean done() {
            if (this.car.getDestinyNode() != null && this.car.getDestinyNode().equals(node)) {
                Utils.sleep(500);
                this.car.setX((int) Double.POSITIVE_INFINITY);

                // Criar a mensagem
                ACLMessage mensagem = new ACLMessage(ACLMessage.INFORM);
                mensagem.setContent("Agente morreu: " + car.getLabel());

                // Definir o destinatário como o AID do Main-Container
                AID mainContainerAID = new AID("mainAgent", AID.ISLOCALNAME);
                mensagem.addReceiver(mainContainerAID);

                // Enviar a mensagem
                send(mensagem);

                // Encerrar o comportamento
                myAgent.doDelete();

                return true;
            }
            return false;
        }
    }
}
