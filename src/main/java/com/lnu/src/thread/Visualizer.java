package com.lnu.src.thread;

import com.lnu.src.model.Floor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Visualizer extends Thread {

  private static final Logger LOGGER = LogManager.getLogger();
  private final int frequencyTime;
  private final List<Floor> floors;
  private final List<Elevator> elevators = new ArrayList<>();


  public Visualizer(int frequencyTime, List<Floor> floors) {
    this.floors = floors;
    this.frequencyTime = frequencyTime;
  }

  public void run() {
    try {
      while (true) {
        Thread.sleep(frequencyTime);
        for (int i = floors.size(); i > 0; i--) {
          LOGGER.info("model.Floor " + (i - 1) + ": " + floors.get(i - 1).toString());
        }
        LOGGER.info("--------------------------------");
        for (int i = 0; i < elevators.size(); i++) {
          LOGGER.info("model.Elevator " + elevators.get(i).toString());
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public void addElevator(Elevator elevator) {
    elevators.add(elevator);
  }
}
