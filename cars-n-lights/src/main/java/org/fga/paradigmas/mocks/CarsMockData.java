package org.fga.paradigmas.mocks;

import org.fga.paradigmas.models.Car;
import org.fga.paradigmas.models.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarsMockData {

    private static final List<Car> CARS = new ArrayList<>();

    static {
        CARS.add(new Car("C1", 160, 62, 1));
        CARS.add(new Car("C2", 60, 80, 1));
        CARS.add(new Car("C3", 120, 230, 1));
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
