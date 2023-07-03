package org.fga.paradigmas.screens;

import org.fga.paradigmas.builders.CityStreetGraphBuilder;
import org.fga.paradigmas.components.GraphStreetComponent;
import org.fga.paradigmas.mocks.NodesMockData;
import org.fga.paradigmas.models.CityGraph;
import org.fga.paradigmas.models.Node;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen(String title, Integer width, Integer height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        this.initMainScreen();
    }

    private void initMainScreen() {
        CityStreetGraphBuilder graphBuilder = new CityStreetGraphBuilder(false);

        CityGraph cityGraph = graphBuilder
                .addStreetNodes(NodesMockData.getNodes())
                .addStreetNodeNeighbor(NodesMockData.get("A"), NodesMockData.get("B"))
                .addStreetNodeNeighbor(NodesMockData.get("A"), NodesMockData.get("D"))
                .addStreetNodeNeighbor(NodesMockData.get("B"), NodesMockData.get("C"))
                .addStreetNodeNeighbor(NodesMockData.get("B"), NodesMockData.get("E"))
                .addStreetNodeNeighbor(NodesMockData.get("C"), NodesMockData.get("F"))
                .addStreetNodeNeighbor(NodesMockData.get("D"), NodesMockData.get("E"))
                .addStreetNodeNeighbor(NodesMockData.get("D"), NodesMockData.get("G"))
                .addStreetNodeNeighbor(NodesMockData.get("E"), NodesMockData.get("F"))
                .addStreetNodeNeighbor(NodesMockData.get("E"), NodesMockData.get("H"))
                .addStreetNodeNeighbor(NodesMockData.get("F"), NodesMockData.get("I"))
                .addStreetNodeNeighbor(NodesMockData.get("G"), NodesMockData.get("H"))
                .addStreetNodeNeighbor(NodesMockData.get("H"), NodesMockData.get("I"))
                .build();

        SwingUtilities.invokeLater(() -> {
            setVisible(true);

            // Componente que gera o grafo
            getContentPane().add(new GraphStreetComponent(cityGraph));
        });
    }

}
