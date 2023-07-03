package org.fga.paradigmas.models;

import java.awt.Color;

enum Direction {
  UP,
  DOWN,
  RIGHT,
  LEFT
}

public class Pedestrian{
  // Posição atual do pedestre
  private Integer posX;
  private Integer posY;
  private String label;
  private Integer speed;
  private Color color;
  private Direction direction;

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

  public void updatePosition(int speed, Direction direction) {
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
