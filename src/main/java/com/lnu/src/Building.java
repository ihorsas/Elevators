package com.lnu.src;


import com.lnu.src.model.Floor;
import com.lnu.src.strategy.ElevatorStrategy;
import com.lnu.src.strategy.StopsOnDemandStrategy;
import com.lnu.src.strategy.StopsOnEveryFloorStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Building {

  private static final Logger LOGGER = LogManager.getLogger();

  static {
//        System.setProperty("java.util.logging.SimpleFormatter.format",
//            "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
  }

  public static void main(String[] args) {
    boolean b1 = true;

    while (b1) {
      try {
        int f = 4; //A number of floors
        int retries = 15; //A number of floors
          if (f < 2) {
              throw new RuntimeException();
          }
        int t = 1000; //An amount of time for lift to move from one floor to another
        float p = 0.7f; //A chance of appearance of a passenger on a random floor (between 0 and 1)
        b1 = false;

        List<Floor> floors = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < f; i++) {
          floors.add(new Floor());
        }

        LOGGER.info("Floors created");

        Thread passengerGenerator = new Thread(new PassengerGenerator(p, t, floors),
            "Passenger_generator_thread");
        passengerGenerator.start();

        ElevatorStrategy stopsOnDemandStrategy = new StopsOnDemandStrategy();
        ElevatorStrategy stopsOnEveryFloorStrategy = new StopsOnEveryFloorStrategy();
        Thread lift1 = new Thread(new Elevator(floors, stopsOnDemandStrategy, t, retries),
            "First_lift_thread");
        Thread lift2 = new Thread(new Elevator(floors, stopsOnEveryFloorStrategy, t, retries),
            "Second_lift_thread");
        lift1.start();
        lift2.start();
      } catch (RuntimeException e) {
        System.out.println("You've entered wrong value, please, try again.");
      }
    }
  }
}
