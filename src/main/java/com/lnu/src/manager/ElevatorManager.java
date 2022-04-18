package com.lnu.src.manager;

import com.lnu.src.model.Floor;
import com.lnu.src.strategy.ElevatorStrategy;
import com.lnu.src.strategy.StopsOnDemandStrategy;
import com.lnu.src.strategy.StopsOnEveryFloorStrategy;
import com.lnu.src.thread.Elevator;

import java.util.List;
import java.util.Objects;

public class ElevatorManager {

  private ElevatorStrategy stopsOnDemandStrategy;
  private ElevatorStrategy stopsOnEveryFloorStrategy;
  private final List<Floor> floors;

  public ElevatorManager(List<Floor> floors) {
    this.floors = floors;
  }

  public Elevator createStopsOnDemandElevator() {
    return new Elevator(floors, getStopsOnDemandStrategy());
  }

  public Elevator createStopsOnEveryFloorElevator() {
    return new Elevator(floors, getStopsOnEveryFloorStrategy());
  }

  private ElevatorStrategy getStopsOnDemandStrategy() {
    if (Objects.isNull(stopsOnDemandStrategy)) {
      stopsOnDemandStrategy = new StopsOnDemandStrategy();
    }
    return stopsOnDemandStrategy;
  }

  private ElevatorStrategy getStopsOnEveryFloorStrategy() {
    if (Objects.isNull(stopsOnEveryFloorStrategy)) {
      stopsOnEveryFloorStrategy = new StopsOnEveryFloorStrategy();
    }
    return stopsOnEveryFloorStrategy;
  }

  public void changeSpeed(Elevator elevator, int newSpeed) {
    // TODO
  }
}
