package org.fga.paradigmas.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.mocks.NodesMockData;
import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.CarDirection;
import org.fga.paradigmas.models.QuadrantIdentification;
import java.lang.Math;
import org.fga.paradigmas.utils.Utils;
import org.fga.paradigmas.models.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        private final Car car;

        public MoveCarBehaviour(Car car) {
            this.car = car;
        }

        @Override
        public void action() {

            // Atualiza a posição do carro
            int carX = car.getX();
            int carY = car.getY();

            double diffx = Math.abs(((double) carX - (double) NodesMockData.get(car.getDestiny()).getX())
                    / ((double) carX + (double) NodesMockData.get(car.getDestiny()).getX())),
                    diffy = Math.abs(((double) carY - (double) NodesMockData.get(car.getDestiny()).getY())
                            / ((double) carY + (double) NodesMockData.get(car.getDestiny()).getY()));
            System.out.println("Executando o agente X: " + carX + " Y: " + carY + " Diffx: " + diffx);
            if (diffx < 0.01 && diffy < 0.01) {
                List<Node> nodes = NodesMockData.getNodes();
                nodes.remove(NodesMockData.get(car.getOrigin()));
                Random rand = new Random();

                Node newDestiny = nodes.get(rand.nextInt(nodes.size()));
                // car.setOrigin(car.getDestiny());
                // car.setDestiny(newDestiny.getLabel());
                // car.setX(NodesMockData.get("D").getX());
                // car.setY(NodesMockData.get("D").getY());
                car.updateCarPosition(car.getSpeed(), CarDirection.DOWN);
            } else {
                car.updateCarPosition(car.getSpeed(), CarDirection.KEEP);
            }

            Utils.sleep(100);
        }

        @Override
        public boolean done() {
            // Condição de termino
            return false;
        }
    }
}
