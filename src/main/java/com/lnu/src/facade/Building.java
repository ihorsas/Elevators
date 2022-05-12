package com.lnu.src.facade;


import static com.lnu.src.constant.Constants.DEFAULT_FLOORS_AMOUNT;
import static com.lnu.src.constant.Constants.DEFAULT_PASSENGER_APPEARANCE_TIME;
import static com.lnu.src.constant.Constants.DEFAULT_PROBABILITY;
import static com.lnu.src.constant.Constants.DEFAULT_VISUALIZER_FREQUENCY_TIME;

import com.lnu.src.manager.ElevatorManager;
import com.lnu.src.manager.ThreadManager;
import com.lnu.src.model.Floor;
import com.lnu.src.thread.Elevator;
import com.lnu.src.thread.Visualizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Building {

  private static final Logger LOGGER = LogManager.getLogger();

  public static void createBuilding() {
    try {
      List<Floor> floors = createFloors(DEFAULT_FLOORS_AMOUNT);
      LOGGER.info("Floors created");
      //create floors
      ElevatorManager elevatorManager = new ElevatorManager(floors);
      Elevator stopsOnDemandLift = elevatorManager.createStopsOnDemandElevator();
      Elevator stopsOnEveryFloorLift = elevatorManager.createStopsOnEveryFloorElevator();
      //create visualizer
      Visualizer visualizer = new Visualizer(DEFAULT_VISUALIZER_FREQUENCY_TIME, floors);
      visualizer.addElevator(stopsOnDemandLift);
      visualizer.addElevator(stopsOnEveryFloorLift);
      //start all threads
      ThreadManager threadManager = new ThreadManager(floors);
      threadManager.startVisualizer(visualizer);
      threadManager.startPassengerRandomizer(DEFAULT_PROBABILITY, DEFAULT_PASSENGER_APPEARANCE_TIME);
      threadManager.startElevator(stopsOnDemandLift);
      threadManager.startElevator(stopsOnEveryFloorLift);
    } catch (RuntimeException e) {
      System.out.println("You've entered wrong value, please, try again.");
    }
  }

  private static List<Floor> createFloors(int amount) {
    List<Floor> floors = Collections.synchronizedList(new ArrayList<>());
    for (int i = 0; i < amount; i++) {
      floors.add(new Floor());
    }
    return floors;
  }
}
