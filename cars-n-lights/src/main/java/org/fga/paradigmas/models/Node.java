package org.fga.paradigmas.models;

import java.util.List;

import org.fga.paradigmas.components.GraphStreetComponent;
import org.fga.paradigmas.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "label" })
public class Node {

    private String label;
    private Integer x;
    private Integer y;
    private List<CarDirection> carDirections;

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

    public CarDirection getCarRandomDirection(CarDirection carDirection) {
        int randomIndex = Utils.getIntRandom(carDirections.size());
        return carDirections.get(randomIndex);

//        switch (carDirection) {
//            case UP:
//                if (randomDirection != CarDirection.DOWN)
//                    carDirectionAux = randomDirection;
//                break;
//            case DOWN:
//                if (randomDirection != CarDirection.UP)
//                    carDirectionAux = randomDirection;
//                break;
//            case RIGHT:
//                if (randomDirection != CarDirection.LEFT)
//                    carDirectionAux = randomDirection;
//                break;
//            case LEFT:
//                if (randomDirection != CarDirection.RIGHT)
//                    carDirectionAux = randomDirection;
//                break;
//        }
//
//        if (carDirectionAux != null)
//            return carDirectionAux;
//
//        return getCarRandomDirection(carDirection);
    }

}
