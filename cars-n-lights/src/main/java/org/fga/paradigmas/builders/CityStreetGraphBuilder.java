package org.fga.paradigmas.builders;

import org.fga.paradigmas.models.CityGraph;
import org.fga.paradigmas.models.Node;

public class CityStreetGraphBuilder {

    private final CityGraph cityGraph;

    public CityStreetGraphBuilder() {
        this.cityGraph = new CityGraph();
    }

    public CityStreetGraphBuilder addStreetNode(Node node) {
        this.cityGraph.addNode(node);
        return this;
    }

    public CityStreetGraphBuilder addStreetNodeNeighbor(Node node, Node neighbor) {
        this.cityGraph.addNodeNeighbor(node, neighbor);
        return this;
    }

    public CityGraph build() {
        return this.cityGraph;
    }

}
