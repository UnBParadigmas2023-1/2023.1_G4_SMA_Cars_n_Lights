package org.fga.paradigmas.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fga.paradigmas.models.Crosswalk;

public class CrosswalkMockData {
    
    private static final List<Crosswalk> CROSSWALK = new ArrayList<>();

    static {
        CROSSWALK.add(new Crosswalk("crosswalkE", false, NodesMockData.get("E"), TrafficLightsMockData.get("trafficLight8"), NodesMockData.get("E").getX() + 10, NodesMockData.get("E").getY() + 100));
    }

    public static Crosswalk get (String label) {
        return CROSSWALK.stream()
            .filter(cw -> cw.getLabel().equals(label))
            .collect(Collectors.toList())
            .get(0);
    }

    public static List<Crosswalk> getCrosswalks() {
        return CROSSWALK;
    }

}