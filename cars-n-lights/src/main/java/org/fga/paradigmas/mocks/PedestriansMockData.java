package org.fga.paradigmas.mocks;

import org.fga.paradigmas.models.Pedestrian;
import org.fga.paradigmas.models.CarDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;

public class PedestriansMockData {
  private static final List<Pedestrian> PEDESTRIANS = new ArrayList<>();

  static {
    PEDESTRIANS.add(new Pedestrian(0, 0, "A", 0, new Color(255, 0, 0), CarDirection.UP));
    PEDESTRIANS.add(new Pedestrian(10, 0, "B", 0, new Color(0, 255, 0), CarDirection.DOWN));
    PEDESTRIANS.add(new Pedestrian(20, 10, "C", 0, new Color(0, 0, 255), CarDirection.RIGHT));
  }

  public static Pedestrian get(String label) {
    return PEDESTRIANS.stream()
                .filter(node -> node.getLabel().equals(label))
                .collect(Collectors.toList())
                .get(0);
  }

  public static List<Pedestrian> getPedestrians() {
        return PEDESTRIANS;
    }
}
