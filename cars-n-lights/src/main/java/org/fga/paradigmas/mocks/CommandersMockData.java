package org.fga.paradigmas.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fga.paradigmas.models.TrafficLight;
import org.fga.paradigmas.models.TrafficLightCommander;

public class CommandersMockData {

    private static final List<TrafficLightCommander> COMMANDERS = new ArrayList<>();
    
    private static final List<TrafficLight> tl_commander1 = new ArrayList<>();
    private static final List<TrafficLight> tl_commander2 = new ArrayList<>();
    private static final List<TrafficLight> tl_commander3 = new ArrayList<>();
    private static final List<TrafficLight> tl_commander4 = new ArrayList<>();
    private static final List<TrafficLight> tl_commander5 = new ArrayList<>();

    static {
        tl_commander1.add(TrafficLightsMockData.get("trafficLight1"));
        tl_commander1.add(TrafficLightsMockData.get("trafficLight4"));
        tl_commander1.add(TrafficLightsMockData.get("trafficLight9"));
        COMMANDERS.add(new TrafficLightCommander("commander1", tl_commander1));

        tl_commander2.add(TrafficLightsMockData.get("trafficLight2"));
        tl_commander2.add(TrafficLightsMockData.get("trafficLight3"));
        COMMANDERS.add(new TrafficLightCommander("commander2", tl_commander2));

        tl_commander3.add(TrafficLightsMockData.get("trafficLight5"));
        tl_commander3.add(TrafficLightsMockData.get("trafficLight6"));
        COMMANDERS.add(new TrafficLightCommander("commander3", tl_commander3));

        tl_commander4.add(TrafficLightsMockData.get("trafficLight7"));
        tl_commander4.add(TrafficLightsMockData.get("trafficLight8"));
        COMMANDERS.add(new TrafficLightCommander("commander4", tl_commander4));

        tl_commander5.add(TrafficLightsMockData.get("trafficLight10"));
        tl_commander5.add(TrafficLightsMockData.get("trafficLight11"));
        tl_commander5.add(TrafficLightsMockData.get("trafficLight12"));
        COMMANDERS.add(new TrafficLightCommander("commander5", tl_commander5));
    }

    public static TrafficLightCommander get (String label) {
        return COMMANDERS.stream()
            .filter(tl -> tl.getLabel().equals(label))
            .collect(Collectors.toList())
            .get(0);
    }

    public static List<TrafficLightCommander> getTrafficLightCommanders() {
        return COMMANDERS;
    }

}
