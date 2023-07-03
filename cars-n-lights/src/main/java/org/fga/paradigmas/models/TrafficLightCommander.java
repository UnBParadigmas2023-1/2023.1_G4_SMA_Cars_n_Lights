package org.fga.paradigmas.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "label" })
public class TrafficLightCommander {

    private String label;
    private List<TrafficLight> trafficLights;

}