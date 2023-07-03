package org.fga.paradigmas.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fga.paradigmas.models.TrafficLight;

public class TrafficLightsMockData {
    
    private static final List<TrafficLight> TRAFFIC_LIGHTS = new ArrayList<>();

    static {
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight1", false, NodesMockData.get("A")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight2", true, NodesMockData.get("B")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight3", false, NodesMockData.get("B")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight4", false, NodesMockData.get("C")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight5", false, NodesMockData.get("D")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight6", true, NodesMockData.get("D")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight7", false, NodesMockData.get("E")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight8", true, NodesMockData.get("E")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight9", false, NodesMockData.get("F")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight10", true, NodesMockData.get("G")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight11", true, NodesMockData.get("H")));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight12", true, NodesMockData.get("I")));
    }

    public static TrafficLight get (String label) {
        return TRAFFIC_LIGHTS.stream()
            .filter(tl -> tl.getLabel().equals(label))
            .collect(Collectors.toList())
            .get(0);
    }

    public static List<TrafficLight> getTrafficLights() {
        return TRAFFIC_LIGHTS;
    }

}
