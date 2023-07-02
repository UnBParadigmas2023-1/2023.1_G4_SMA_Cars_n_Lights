package org.fga.paradigmas.mocks;

import org.fga.paradigmas.models.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NodesMockData {

    private static final List<Node> NODES = new ArrayList<>();

    static {
        NODES.add(new Node("A", 50, 50));
        NODES.add(new Node("B", 50, 200));
        NODES.add(new Node("C", 50, 500));
        NODES.add(new Node("D", 250, 50));
        NODES.add(new Node("E", 250, 200));
        NODES.add(new Node("F", 250, 500));
        NODES.add(new Node("G", 550, 50));
        NODES.add(new Node("H", 550, 200));
        NODES.add(new Node("I", 550, 500));
    }

    public static Node get(String label) {
        return NODES.stream()
                .filter(node -> node.getLabel().equals(label))
                .collect(Collectors.toList())
                .get(0);
    }

    public static List<Node> getNodes() {
        return NODES;
    }

}
