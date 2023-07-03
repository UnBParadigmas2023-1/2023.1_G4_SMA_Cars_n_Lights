package org.fga.paradigmas.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.fga.paradigmas.mocks.CarsMockData;
import org.fga.paradigmas.mocks.TrafficLightsMockData;
import org.fga.paradigmas.models.CityGraph;
import org.fga.paradigmas.models.Node;

public class GraphStreetComponent extends JPanel {

    public static final Float STROKE_WIDTH = 50f;
    public static final Integer SQUARE_SIZE = 75;
    public static final Integer LINE_SPACING = 20;
    private static final Integer ELLIPSE_SIZE = 10;

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

        // Desenha os semaforos
        cityGraph.getNodes().forEach(this::drawTrafficLights);

        // Muda a cor dos semaforos
        changeColor();

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
                g2d.drawLine(x1, y1, x2, y2);
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
            int x = car.getX() + (SQUARE_SIZE) / 2 - 8;
            int y = car.getY() + (SQUARE_SIZE) / 2 - 8;

            g2d.setColor(car.getColor());
            g2d.fill(new Rectangle(x, y, 15, 15));
        });
    }

    private void drawTrafficLights(Node node) {
        int x, y;
        Color cor = Color.RED;

        switch (node.getLabel()) {

            case "A":
            case "B":
            case "D":
            case "E":

                x = node.getX() + 35;
                y = node.getY() + 75;

                g2d.setColor(cor);
                g2d.fill(new Ellipse2D.Double(x, y, ELLIPSE_SIZE, ELLIPSE_SIZE));
                break;

            default:
                break;
        }

        switch (node.getLabel()) {

            case "B":
            case "C":
            case "E":
            case "F":

                x = node.getX() + 75;
                y = node.getY() + 35;

                g2d.setColor(cor);
                g2d.fill(new Ellipse2D.Double(x, y, ELLIPSE_SIZE, ELLIPSE_SIZE));
                break;

            default:
                break;
        }

        if (node.getLabel() == "D" || node.getLabel() == "G") {
            x = node.getX() - 5;
            y = node.getY() + 35;

            g2d.setColor(cor);
            g2d.fill(new Ellipse2D.Double(x, y, ELLIPSE_SIZE, ELLIPSE_SIZE));
        }

        if (node.getLabel() == "H" || node.getLabel() == "I") {
            x = node.getX() + 35;
            y = node.getY() - 5;

            g2d.setColor(cor);
            g2d.fill(new Ellipse2D.Double(x, y, ELLIPSE_SIZE, ELLIPSE_SIZE));
        }
    }


    private void updateTrafficLights(Node node, String trafficLightName, Color cor) {
        int x = 0;
        int y = 0;

        switch (trafficLightName) {

            case "trafficLight1":
            case "trafficLight6":
            case "trafficLight3":
            case "trafficLight8":

                x = node.getX() + 35;
                y = node.getY() + 75;

                break;
            
            case "trafficLight2":
            case "trafficLight7":
            case "trafficLight4":
            case "trafficLight9":

                x = node.getX() + 75;
                y = node.getY() + 35;

                break;

            case "trafficLight5":
            case "trafficLight10":

                x = node.getX() - 5;
                y = node.getY() + 35;

                break;

            case "trafficLight11":
            case "trafficLight12": 

                x = node.getX() + 35;
                y = node.getY() - 5;

                break;
        
            default:
                break;
        }

        g2d.setColor(cor);
        g2d.fill(new Ellipse2D.Double(x, y, ELLIPSE_SIZE, ELLIPSE_SIZE));
    }

    private void updateScreen() {
        this.timer = new Timer(100, (listener) -> {
            timer.stop();
            repaint();
        });

        timer.start();
    }

    public void changeColor () {
        TrafficLightsMockData.getTrafficLights().forEach(trafficLight -> {
            boolean state = trafficLight.getState();
            if(state) updateTrafficLights(trafficLight.getNode(), trafficLight.getLabel(), Color.GREEN);
            else updateTrafficLights(trafficLight.getNode(), trafficLight.getLabel(), Color.RED);
        });

    }
}
