package com.lnu.src.strategy;


import com.lnu.src.model.Passenger;

import java.util.List;

public interface ElevatorStrategy {

  boolean keepGoing(List<Passenger> passengersLift, int currentFloor);
}
