package org.fga.paradigmas.screens;

import org.fga.paradigmas.builders.CityStreetGraphBuilder;
import org.fga.paradigmas.components.GraphStreetComponent;
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
        Node n1 = new Node("A", 50, 50);
        Node n2 = new Node("B", 50, 500);

        CityStreetGraphBuilder graphBuilder = new CityStreetGraphBuilder();

        CityGraph cityGraph = graphBuilder
                .addStreetNode(n1)
                .addStreetNode(n2)
                .addStreetNodeNeighbor(n1, n2)
                .addStreetNodeNeighbor(n2, n1)
                .build();

        SwingUtilities.invokeLater(() -> {
            setVisible(true);

            // Componente que gera o grafo
            getContentPane().add(new GraphStreetComponent(cityGraph));
        });
    }

}
