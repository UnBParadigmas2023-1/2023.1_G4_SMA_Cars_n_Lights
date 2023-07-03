package org.fga.paradigmas.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fga.paradigmas.models.TrafficLight;

public class TrafficLightsMockData {
    
    private static final List<TrafficLight> TRAFFIC_LIGHTS = new ArrayList<>();

    static {
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight1", false, NodesMockData.get("A"), NodesMockData.get("A").getX() + 35, NodesMockData.get("A").getY() + 75));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight2", true, NodesMockData.get("B"), NodesMockData.get("B").getX() + 75, NodesMockData.get("B").getY() + 35));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight3", false, NodesMockData.get("B"), NodesMockData.get("B").getX() + 35, NodesMockData.get("B").getY() + 75));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight4", false, NodesMockData.get("C"), NodesMockData.get("C").getX() + 75, NodesMockData.get("C").getY() + 35));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight5", false, NodesMockData.get("D"), NodesMockData.get("D").getX() - 5, NodesMockData.get("D").getY() + 35));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight6", true, NodesMockData.get("D"), NodesMockData.get("D").getX() + 35, NodesMockData.get("D").getY() + 75));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight7", false, NodesMockData.get("E"), NodesMockData.get("E").getX() + 75, NodesMockData.get("E").getY() + 35));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight8", true, NodesMockData.get("E"), NodesMockData.get("E").getX() + 35, NodesMockData.get("E").getY() + 75));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight9", false, NodesMockData.get("F"), NodesMockData.get("F").getX() + 75, NodesMockData.get("F").getY() + 35));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight10", true, NodesMockData.get("G"), NodesMockData.get("G").getX() - 5, NodesMockData.get("G").getY() + 35));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight11", true, NodesMockData.get("H"), NodesMockData.get("H").getX() + 35, NodesMockData.get("H").getY() - 5));
        TRAFFIC_LIGHTS.add(new TrafficLight("trafficLight12", true, NodesMockData.get("I"), NodesMockData.get("I").getX() + 35, NodesMockData.get("I").getY() - 5));
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
