package com.lnu.src.strategy;

import com.lnu.src.model.Floor;
import com.lnu.src.thread.Elevator;

import java.util.List;

public class ElevatorContext {

  private final List<Floor> floors;
  private ElevatorStrategy strategy;

  public ElevatorContext(List<Floor> floors) {
    this.floors = floors;
  }

  public void setStrategy(ElevatorStrategy strategy) {
    this.strategy = strategy;
  }

  public Elevator createElevator() {
    return new Elevator(floors, strategy);
  }
}
