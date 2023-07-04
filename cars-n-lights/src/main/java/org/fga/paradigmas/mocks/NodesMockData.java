package org.fga.paradigmas.mocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.fga.paradigmas.models.CarDirection;
import org.fga.paradigmas.models.Node;
import org.fga.paradigmas.utils.Utils;

public class NodesMockData {

    private static final List<Node> NODES = new ArrayList<>();

    static {
        NODES.add(new Node("A", 50, 50, Arrays.asList(CarDirection.RIGHT)));
        NODES.add(new Node("B", 50, 200, Arrays.asList(CarDirection.UP)));
        NODES.add(new Node("C", 50, 500, Arrays.asList(CarDirection.UP)));
        NODES.add(new Node("D", 250, 50, Arrays.asList(CarDirection.RIGHT)));
        NODES.add(new Node("E", 250, 200, Arrays.asList(CarDirection.LEFT, CarDirection.UP)));
        NODES.add(new Node("F", 250, 500, Arrays.asList(CarDirection.LEFT, CarDirection.UP)));
        NODES.add(new Node("G", 550, 50, Arrays.asList(CarDirection.DOWN)));
        NODES.add(new Node("H", 550, 200, Arrays.asList(CarDirection.LEFT, CarDirection.DOWN)));
        NODES.add(new Node("I", 550, 500, Arrays.asList(CarDirection.LEFT)));
    }

    public static Node get(String label) {
        List<Node> nodes = NODES.stream()
                .filter(node -> node.getLabel().equals(label))
                .collect(Collectors.toList());

        if (!nodes.isEmpty()) {
            return nodes.get(0);
        }

        return null;
    }

    public static List<Node> getNodes() {
        return NODES;
    }

    public static Node getNodeByCoordinates(int x, int y) {
        List<Node> nodes = NODES
                .stream()
                .filter(node -> node.getX().equals(x) && node.getY().equals(y))
                .collect(Collectors.toList());

        if (!nodes.isEmpty()) {
            return nodes.get(0);
        }

        return null;
    }

    public static Node getRandomNode() {
        int randomIndex = Utils.getIntRandom(NODES.size() - 2);
        return NODES.get(randomIndex);
    }

}
