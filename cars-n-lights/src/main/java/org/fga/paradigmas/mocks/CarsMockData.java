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
        CARS.add(new Car("C1", NodesMockData.get("A").getX(), NodesMockData.get("A").getY(), 5, Color.RED, CarDirection.RIGHT, NodesMockData.get("H")));
        CARS.add(new Car("C2", NodesMockData.get("B").getX(), NodesMockData.get("B").getY(), 5, Color.BLUE, CarDirection.DOWN, NodesMockData.get("A")));
        CARS.add(new Car("C3", NodesMockData.get("C").getX(), NodesMockData.get("C").getY(), 5, Color.GREEN, CarDirection.RIGHT, NodesMockData.get("G")));
        CARS.add(new Car("C4", NodesMockData.get("C").getX(), NodesMockData.get("C").getY(), 1, Color.MAGENTA, CarDirection.RIGHT, NodesMockData.get("Z")));
        CARS.add(new Car("C5", NodesMockData.get("G").getX(), NodesMockData.get("G").getY(), 1, Color.MAGENTA, CarDirection.RIGHT, NodesMockData.get("Z")));
        CARS.add(new Car("C6", NodesMockData.get("H").getX(), NodesMockData.get("H").getY(), 1, Color.MAGENTA, CarDirection.RIGHT, NodesMockData.get("Z")));
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
