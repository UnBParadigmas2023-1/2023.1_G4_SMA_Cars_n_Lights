package org.fga.paradigmas.mocks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.CarDirection;

public class CarsMockData {

    private static final List<Car> CARS = new ArrayList<>();

    static {
        // speed: múltiplos de 5 ou 1 :)
        CARS.add(new Car("C1", NodesMockData.get("A").getX(), NodesMockData.get("A").getY(), 5, Color.BLUE,
                CarDirection.RIGHT, "A", "G"));
        // CARS.add(new Car("C2",
        // NodesMockData.get("B").getQuadrant(QuadrantIdentification.Q2).getX(),
        // NodesMockData.get("B").getQuadrant(QuadrantIdentification.Q2).getY(), 1,
        // Color.RED, null));
        // CARS.add(new Car("C3",
        // NodesMockData.get("C").getQuadrant(QuadrantIdentification.Q4).getX(),
        // NodesMockData.get("C").getQuadrant(QuadrantIdentification.Q4).getY(), 1,
        // Color.GREEN, null));
        // CARS.add(new Car("C4",
        // NodesMockData.get("D").getQuadrant(QuadrantIdentification.Q4).getX(),
        // NodesMockData.get("D").getQuadrant(QuadrantIdentification.Q4).getY(), 1,
        // Color.MAGENTA, null));
    }

    public static Car get(String label) {
        return CARS.stream()
                .filter(node -> node.getLabel().equals(label))
                .collect(Collectors.toList())
                .get(0);
    }

    public static List<Car> getCars() {
        return CARS;
    }

}