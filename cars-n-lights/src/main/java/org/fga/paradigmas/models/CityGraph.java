package org.fga.paradigmas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityGraph {

    private Map<Node, List<Node>> graph = new HashMap<>();

    public List<Node> getNodes() {
        return new ArrayList<>(graph.keySet());
    }

    public List<Node> getNeighbors(Node node) {
        return graph.get(node);
    }

    public void addNode(Node node) {
        this.graph.put(node, new ArrayList<>());
    }

    public void addNodeNeighbor(Node node, Node neighbor) {
        this.graph.get(node).add(neighbor);
    }

    public boolean isNeighbor(Node node, Node neighborCandidate) {
        return getNeighbors(node)
                .stream()
                .anyMatch(neighbors -> neighbors.getLabel().equals(neighborCandidate.getLabel()));
    }

}
