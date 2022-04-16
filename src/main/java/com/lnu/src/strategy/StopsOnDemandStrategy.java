package com.lnu.src.strategy;


import com.lnu.src.model.Passenger;

import java.util.List;

public class StopsOnDemandStrategy implements ElevatorStrategy {

  @Override
  public boolean keepGoing(List<Passenger> passengersLift, int currentFloor) {
    if (passengersLift.isEmpty()) {
      return false;
    }
    for (Passenger passenger : passengersLift) {
      if (passenger.getTo() == currentFloor) {
        return false;
      }
    }
    return true;
  }
}
