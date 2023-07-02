package org.fga.paradigmas.builders;

import org.fga.paradigmas.models.CityGraph;
import org.fga.paradigmas.models.Node;

import java.util.List;

public class CityStreetGraphBuilder {

    private final CityGraph cityGraph;
    private final boolean isStrongConnected;

    public CityStreetGraphBuilder(boolean isStrongConnected) {
        this.cityGraph = new CityGraph();
        this.isStrongConnected = isStrongConnected;
    }

    public CityStreetGraphBuilder addStreetNode(Node node) {
        this.cityGraph.addNode(node);
        return this;
    }

    public CityStreetGraphBuilder addStreetNodes(List<Node> nodes) {
        nodes.forEach(this.cityGraph::addNode);
        return this;
    }

    public CityStreetGraphBuilder addStreetNodeNeighbor(Node node, Node neighbor) {
        if (isStrongConnected) {
            this.cityGraph.addNodeNeighbor(node, neighbor);
            this.cityGraph.addNodeNeighbor(neighbor, node);
        } else {
            this.cityGraph.addNodeNeighbor(node, neighbor);
        }
        return this;
    }

    public CityGraph build() {
        return this.cityGraph;
    }

}
