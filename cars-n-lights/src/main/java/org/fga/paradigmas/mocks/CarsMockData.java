package org.fga.paradigmas.mocks;

import org.fga.paradigmas.components.GraphStreetComponent;
import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.CarDirection;
import org.fga.paradigmas.models.Node;
import org.fga.paradigmas.models.QuadrantIdentification;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarsMockData {

    private static final List<Car> CARS = new ArrayList<>();

    static {
        CARS.add(new Car("C1", NodesMockData.get("A").getX(), NodesMockData.get("A").getY(), 3, Color.BLUE, CarDirection.RIGHT));
//        CARS.add(new Car("C2", NodesMockData.get("B").getQuadrant(QuadrantIdentification.Q2).getX(), NodesMockData.get("B").getQuadrant(QuadrantIdentification.Q2).getY(), 1, Color.RED, null));
//        CARS.add(new Car("C3", NodesMockData.get("C").getQuadrant(QuadrantIdentification.Q4).getX(), NodesMockData.get("C").getQuadrant(QuadrantIdentification.Q4).getY(), 1, Color.GREEN, null));
//        CARS.add(new Car("C4", NodesMockData.get("D").getQuadrant(QuadrantIdentification.Q4).getX(), NodesMockData.get("D").getQuadrant(QuadrantIdentification.Q4).getY(), 1, Color.MAGENTA, null));
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
