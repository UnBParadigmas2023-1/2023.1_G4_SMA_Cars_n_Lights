package org.fga.paradigmas.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.models.CityGraph;
import org.fga.paradigmas.models.Node;

public class GraphStreetComponent extends JPanel {

    public static final Float STROKE_WIDTH = 35f;
    public static final Integer SQUARE_SIZE = 75;
    public static final Integer LINE_SPACING = 20;

    private Graphics2D g2d;
    private Timer timer;
    private final CityGraph cityGraph;

    public GraphStreetComponent(final CityGraph cityGraph) {
        this.cityGraph = cityGraph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));

        // Desenha o grafo com as conexões
        cityGraph.getGraph().forEach(this::drawConnections);
        cityGraph.getNodes().forEach(this::drawNodes);

        // Desenha o carro
        drawCar();

        // Atualizar a tela
        updateScreen();
    }

    private void drawConnections(Node node, java.util.List<Node> neighbors) {
        int halfSizeOfSquare = SQUARE_SIZE / 2;

        int x1 = node.getX() + halfSizeOfSquare;
        int y1 = node.getY() + halfSizeOfSquare;

        neighbors.forEach(neighbor -> {
            int x2 = neighbor.getX() + halfSizeOfSquare;
            int y2 = neighbor.getY() + halfSizeOfSquare;

            // Caso A e B tenham uma conexão
            if (cityGraph.isNeighbor(node, neighbor)) {
                // linha de ida
                g2d.drawLine(x1 + LINE_SPACING, y1 + LINE_SPACING, x2 + LINE_SPACING, y2 + LINE_SPACING);
            }

            // Caso B e A tenham uma conexão
            if (cityGraph.isNeighbor(neighbor, node)) {
                // linha de volta
                g2d.drawLine(x1 - LINE_SPACING, y1 - LINE_SPACING, x2 - LINE_SPACING, y2 - LINE_SPACING);
            }
        });
    }

    private void drawNodes(Node node) {
        int x = node.getX();
        int y = node.getY();

        g2d.setColor(Color.GRAY);
        g2d.fill(new Rectangle(x, y, SQUARE_SIZE, SQUARE_SIZE));

        g2d.setColor(Color.WHITE);

        // Obtém as dimensões do texto
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(node.getLabel());
        int textHeight = fm.getHeight();

        // Calcula a posição central do texto
        int textX = x + (SQUARE_SIZE - textWidth) / 2;
        int textY = y + (SQUARE_SIZE - textHeight) / 2 + fm.getAscent();

        g2d.drawString(node.getLabel(), textX, textY);
    }

    private void drawCar() {
        CarsMockData.getCars().forEach(car -> {
            int x = car.getX(), y = car.getY();

            g2d.setColor(car.getColor());
            g2d.fill(new Rectangle(x, y, 15, 15));
        });
    }

    private void updateScreen() {
        this.timer = new Timer(100, (listener) -> {
            timer.stop();
            repaint();
        });

        timer.start();
    }
}
