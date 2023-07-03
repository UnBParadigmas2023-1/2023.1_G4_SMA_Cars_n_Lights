package org.fga.paradigmas.agents;

import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.mocks.NodesMockData;
import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.CarDirection;
import org.fga.paradigmas.models.Node;
import org.fga.paradigmas.utils.Utils;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class CarAgent extends Agent {

    private final Car car;

    public CarAgent() {
        this.car = CarsMockData.get("C1");
    }

    @Override
    protected void setup() {
        System.out.println("Car Agent");

        addBehaviour(new MoveCarBehaviour(car));
    }

    private static class MoveCarBehaviour extends Behaviour {

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
                System.out.println("Carro indo para: " + direction);
            } else {
                car.updateCarPosition(car.getSpeed(), CarDirection.KEEP);
            }

            Utils.sleep(100);
        }

        @Override
        public boolean done() {
            if (this.car.getDestinyNode().equals(node)) {
                Utils.sleep(500);
                this.car.setX((int) Double.POSITIVE_INFINITY);
                return true;
            }
            return false;
        }
    }
}
