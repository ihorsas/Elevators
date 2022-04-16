package com.lnu.src;


import com.lnu.src.model.Floor;
import com.lnu.src.model.Passenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PassengerGenerator extends Thread {

  private static final Logger LOGGER = LogManager.getLogger();
  private final float chance;
  private final int time;
  private final List<Floor> floors;


  PassengerGenerator(float chance, int time, List<Floor> floors) {
    this.chance = chance;
    this.floors = floors;
    this.time = time;
  }

  private void generatePassenger() throws InterruptedException {
    int from = ThreadLocalRandom.current().nextInt(floors.size());
    int to = ThreadLocalRandom.current().nextInt(floors.size());
    while (from == to) {
      to = ThreadLocalRandom.current().nextInt(floors.size());
    }
    Passenger passenger = new Passenger(from, to);
    floors.get(from).addPassenger(passenger);
    LOGGER.info("New passenger added to floor " + from + ": " + passenger + "\n");
  }

  public void run() {
    try {
      while (true) {
        Thread.sleep(time);
        for (int i = floors.size(); i > 0; i--) {
          LOGGER.info("model.Floor " + (i - 1) + ": " + floors.get(i - 1).toString());
        }
        if (ThreadLocalRandom.current().nextFloat() < chance) {
          generatePassenger();
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
