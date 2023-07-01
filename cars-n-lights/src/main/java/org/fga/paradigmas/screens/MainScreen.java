package org.fga.paradigmas.screens;

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
        SwingUtilities.invokeLater(() -> {
            setVisible(true);

            // Componente que gera o grafo
            getContentPane().add(new Label("Ola, mundo"));
        });
    }

}
