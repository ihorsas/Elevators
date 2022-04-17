package com.lnu.src.thread;

import static com.lnu.src.constant.Constants.DEFAULT_ELEVATOR_CAPACITY;
import static com.lnu.src.constant.Constants.DEFAULT_ELEVATOR_SPEED;
import static com.lnu.src.constant.Constants.DEFAULT_ELEVATOR_WAITING_TIME;
import static com.lnu.src.constant.Constants.DEFAULT_PASSENGER_ENTRY_EXIT_TIME;
import static com.lnu.src.constant.Constants.ZERO_FLOOR;

import com.lnu.src.model.Floor;
import com.lnu.src.model.Passenger;
import com.lnu.src.strategy.ElevatorStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Elevator extends Thread {

  private static final Logger LOGGER = LogManager.getLogger();
  private final int maxCapacity = DEFAULT_ELEVATOR_CAPACITY; //Maximum number of people
  private final List<Floor> floors; //List with floors
  private final List<Passenger> passengersLift = new ArrayList<>(); //Passenger list in the elevator
  private int speed = DEFAULT_ELEVATOR_SPEED; //Time for elevator to get from one floor to another
  private int entryExitTime = DEFAULT_PASSENGER_ENTRY_EXIT_TIME; //Time for passenger entry / exit
  private final ElevatorStrategy strategy;
  private int currentFloor = ZERO_FLOOR; //The current floor
  private boolean goingUp = true; //Driving direction

  public Elevator(List<Floor> floors, ElevatorStrategy strategy) {
    this.floors = floors;
    this.strategy = strategy;
  }

  public void run() {
    try {
      while (true) {
        //
        while (floors.isEmpty() && passengersLift.isEmpty() && currentFloor == 0) {
          Thread.sleep(DEFAULT_ELEVATOR_WAITING_TIME);
        }

        boolean keepGoing = strategy.keepGoing(passengersLift, currentFloor);
        if (!keepGoing) {
          releasePassengers();
          takePassengers();
        }

        goingUp = getDirection();
        //Going up/down by 1 floor
        if (goingUp) {
          currentFloor++;
          Thread.sleep(speed);
        } else {
          currentFloor--;
          Thread.sleep(speed);
        }
      }

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private boolean getDirection() {
    if (passengersLift.isEmpty()) {
      if (goingUp) {
        // if going up then firstly check floors above
        for (int i = currentFloor; i < floors.size(); i++) {
          if (floors.get(i).hasPassengers()) {
            return true;
          }
        }
        for (int i = currentFloor; i >= 0; i--) {
          if (floors.get(i).hasPassengers()) {
            return false;
          }
        }
      } else {
        // if going down then firstly check floors below
        for (int i = currentFloor; i >= 0; i--) {
          if (floors.get(i).hasPassengers()) {
            return false;
          }
        }
        for (int i = currentFloor; i < floors.size(); i++) {
          if (floors.get(i).hasPassengers()) {
            return true;
          }
        }
      }
      //if there's no passengers in lift and on any floors then we go down to the first floor
      return currentFloor == 0;
    } else {
      if ((goingUp && currentFloor == floors.size() - 1) || (!goingUp && currentFloor == 0)) {
        return !goingUp;
      } else {
        return goingUp;
      }
    }

  }

  private void releasePassengers() throws InterruptedException {
    if (passengersLift.size() != 0) {
      //If there are passengers int the elevator - checking, if someone can exit
      Iterator<Passenger> i = passengersLift.iterator();
      while (i.hasNext()) {
        // must be called before you can call i.remove()
        if (i.next().getTo() == currentFloor) {
          i.remove();
          Thread.sleep(entryExitTime);
        }
      }
    }
  }

  public void takePassengers() throws InterruptedException {
    //Takes only those passengers, who have the same direction, as elevator
    if (goingUp) {
      takePassengersUp();
      // if there was no passengers with the same direction take others
      if (passengersLift.isEmpty()) {
        takePassengersDown();
        goingUp = false;
      }
    } else {
      takePassengersDown();
      if (passengersLift.isEmpty()) {
        takePassengersUp();
        goingUp = true;
      }
    }
  }

  private void takePassengersUp() throws InterruptedException {
    while (floors.get(currentFloor).getListPassengersUp().size() > 0) {
      if (passengersLift.size()
          == maxCapacity) //Checking, if there is too many passengers in the elevator
      {
        break;
      }
      Passenger passenger = floors.get(currentFloor).getPassengerUp();
      if (!Objects.isNull(passenger)) {
        passengersLift.add(passenger); //Adding a passenger
      }
      LOGGER.info("New user has entered in this lift");
      Thread.sleep(entryExitTime);
    }
  }

  private void takePassengersDown() throws InterruptedException {
    while (floors.get(currentFloor).getListPassengersDown().size() > 0) {
      if (passengersLift.size() == maxCapacity) //The same
      {
        break;
      }
      Passenger passenger = floors.get(currentFloor).getPassengerDown();
      if (!Objects.isNull(passenger)) {
        passengersLift.add(passenger);
      }
      Thread.sleep(entryExitTime);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Elevator elevator = (Elevator) o;
    return maxCapacity == elevator.maxCapacity &&
        speed == elevator.speed &&
        entryExitTime == elevator.entryExitTime &&
        currentFloor == elevator.currentFloor &&
        goingUp == elevator.goingUp &&
        Objects.equals(floors, elevator.floors) &&
        Objects.equals(passengersLift, elevator.passengersLift) &&
        Objects.equals(strategy, elevator.strategy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxCapacity, speed, entryExitTime, strategy);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Elevator{");
    sb.append("hashcode=").append(hashCode());
    sb.append(", currentFloor=").append(currentFloor);
    sb.append(", goingUp=").append(goingUp);
    sb.append(", passengersLift=").append(passengersLift);
    sb.append(", speed=").append(speed);
    sb.append('}');
    return sb.toString();
  }
}
