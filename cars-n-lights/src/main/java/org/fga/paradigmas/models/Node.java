package org.fga.paradigmas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fga.paradigmas.components.GraphStreetComponent;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "label" })
public class Node {

    private String label;
    private Integer x;
    private Integer y;
    // private CarDirection dir;

    public Quadrant getQuadrant(QuadrantIdentification identification) {
        int x = 0, y = 0;
        int squareCenterX = this.x + (GraphStreetComponent.SQUARE_SIZE / 2);
        int squareCenterY = this.y + (GraphStreetComponent.SQUARE_SIZE / 2);

        // metade da metade do lado do quadrado
        // o valor 10 corrige a geração do quadrado para o centro do quadrante
        int squareHalfSide = (GraphStreetComponent.SQUARE_SIZE / 4);

        switch (identification) {
            case Q1:
                x = squareCenterX - squareHalfSide - 10;
                y = squareCenterY - squareHalfSide - 10;
                break;

            case Q2:
                x = squareCenterX + squareHalfSide - 10;
                y = squareCenterY - squareHalfSide - 10;
                break;

            case Q3:
                x = squareCenterX - squareHalfSide - 10;
                y = squareCenterY + squareHalfSide - 10;
                break;

            case Q4:
                x = squareCenterX + squareHalfSide - 10;
                y = squareCenterY + squareHalfSide - 10;
                break;
        }

        return new Quadrant(identification, x, y);
    }

}
