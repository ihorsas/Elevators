package com.lnu.src.manager;

import com.lnu.src.model.Floor;
import com.lnu.src.strategy.ElevatorContext;
import com.lnu.src.strategy.ElevatorStrategy;
import com.lnu.src.strategy.StopsOnDemandStrategy;
import com.lnu.src.strategy.StopsOnEveryFloorStrategy;
import com.lnu.src.thread.Elevator;

import java.util.List;
import java.util.Objects;

public class ElevatorManager {

  private ElevatorStrategy stopsOnDemandStrategy;
  private ElevatorStrategy stopsOnEveryFloorStrategy;
  private final ElevatorContext context;

  public ElevatorManager(List<Floor> floors) {
    this.context = new ElevatorContext(floors);
  }

  public Elevator createStopsOnDemandElevator() {
    context.setStrategy(getStopsOnDemandStrategy());
    return context.createElevator();
  }

  public Elevator createStopsOnEveryFloorElevator() {
    context.setStrategy(getStopsOnEveryFloorStrategy());
    return context.createElevator();
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
