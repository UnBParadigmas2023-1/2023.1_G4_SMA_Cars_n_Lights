package org.fga.paradigmas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"label"})
public class Car {

    private String label;
    private Integer x;
    private Integer y;
    private Integer speed;
    private Color color;
    private CarDirection carDirection;

    public void updateCarPosition(int speed, CarDirection carDirection)  {
        if (carDirection != CarDirection.KEEP) {
            this.carDirection = carDirection;
        }

        switch (this.carDirection) {
            case UP:
                // descer
                this.setX(this.x += 0);
                this.setY(this.y -= speed);
                break;

            case DOWN:
                // subir
                this.setX(this.x += 0);
                this.setY(this.y += speed);
                break;

            case RIGHT:
                // direita
                this.setX(this.x += speed);
                this.setY(this.y += 0);
                break;

            case LEFT:
                // esquerda
                this.setX(this.x -= speed);
                this.setY(this.y += 0);
                break;
        }
    }

}
