package org.fga.paradigmas.models;

import java.awt.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "label" })
public class Pedestrian{
  // Posição atual do pedestre
  private Integer posX;
  private Integer posY;
  private String label;
  private Integer speed;
  private Color color;
  private CarDirection direction;

  // Getters e Setters
  private void setPosX(Integer posX) {
    this.posX = posX;
  }
  public Integer getPosX() {
    return this.posX;
  }

  private void setPosY(Integer posY) {
    this.posY = posY;
  }
  public Integer getPosY() {
    return this.posY;
  }

  private void setLabel(String label) {
    this.label = label;
  }
  public String getLabel() {
    return this.label;
  }

  private void setSpeed(Integer speed) {
    this.speed = speed;
  }
  private Integer getSpeed() {
    return this.speed;
  }

  private void setColor(Color color) {
    this.color = color;
  }
  public Color getColor() {
    return this.color;
  }

  public void updatePosition(int speed, CarDirection direction) {
    switch (this.direction) {
      case UP:
        // Go upwards
        this.setPosY(this.posY+=speed);
      case DOWN:
        // Go downwards 
        this.setPosY(this.posY-=speed);
      case RIGHT:
        // Go downwards 
        this.setPosX(this.posX+=speed);
      case LEFT:
        // Go downwards 
        this.setPosX(this.posX-=speed);
      default:
        // Default case, don't to nothing
        System.out.println("Pedestrian waiting to move.");
    }
  }
}
