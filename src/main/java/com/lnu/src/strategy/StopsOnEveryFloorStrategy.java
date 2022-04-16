package com.lnu.src.strategy;


import com.lnu.src.model.Passenger;

import java.util.List;

public class StopsOnEveryFloorStrategy implements ElevatorStrategy {

  @Override
  public boolean keepGoing(List<Passenger> passengersLift, int currentFloor) {
    return false;
  }
}
