package org.fga.paradigmas.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.mocks.NodesMockData;
import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.CarDirection;
import org.fga.paradigmas.models.QuadrantIdentification;
import org.fga.paradigmas.utils.Utils;

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
            System.out.println("Executando o agente");

            // Atualiza a posição do carro
            int carX = car.getX();
            int carY = car.getY();

            if (carX == NodesMockData.get("D").getQuadrant(QuadrantIdentification.Q2).getX() && carY == NodesMockData.get("D").getQuadrant(QuadrantIdentification.Q2).getY()) {
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
