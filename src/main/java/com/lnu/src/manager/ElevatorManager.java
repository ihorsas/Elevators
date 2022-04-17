package com.lnu.src.manager;

import com.lnu.src.model.Floor;
import com.lnu.src.strategy.ElevatorStrategy;
import com.lnu.src.strategy.StopsOnEveryFloorStrategy;
import com.lnu.src.thread.Elevator;

import java.util.List;

public class ElevatorManager {

  private final ElevatorStrategy stopsOnDemandStrategy = new StopsOnEveryFloorStrategy();
  private final ElevatorStrategy stopsOnEveryFloorStrategy = new StopsOnEveryFloorStrategy();
  private final List<Floor> floors;

  public ElevatorManager(List<Floor> floors) {
    this.floors = floors;
  }

  public Elevator createStopsOnDemandElevator() {
    return new Elevator(floors, stopsOnDemandStrategy);
  }

  public Elevator createStopsOnEveryFloorElevator() {
    return new Elevator(floors, stopsOnEveryFloorStrategy);
  }

  public void changeSpeed(Elevator elevator, int newSpeed) {
    // TODO
  }
}
