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


    public boolean getState() {
        return this.state;
    }

    public void updateState(boolean newState) {
        this.state = newState;
    }

}