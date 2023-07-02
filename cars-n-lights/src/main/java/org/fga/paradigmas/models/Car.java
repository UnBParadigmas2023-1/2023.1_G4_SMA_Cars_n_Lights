package org.fga.paradigmas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"label"})
public class Car {

    private String label;
    private Integer x;
    private Integer y;
    private Integer speed;

    public void updateCarPosition(int newX, int newY, CarDirection carDirection)  {
        switch (carDirection) {
            case UP:
                // subir
                this.setX(this.x += 0);
                this.setY(this.y += newY);
                break;

            case DOWN:
                // descer
                this.setX(this.x += 0);
                this.setY(this.y -= newY);
                break;

            case RIGHT:
                // direita
                this.setX(this.x += newX);
                this.setY(this.y += 0);
                break;

            case LEFT:
                // esquerda
                this.setX(this.x -= newX);
                this.setY(this.y += 0);
                break;
        }
    }

}
