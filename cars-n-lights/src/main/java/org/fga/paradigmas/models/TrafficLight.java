package org.fga.paradigmas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "label" })
public class TrafficLight {

    private String label;
    private boolean state;
    private Node node;
    private Integer x;
    private Integer y;


    public boolean getState() {
        return this.state;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public void updateState(boolean newState) {
        this.state = newState;
    }

}