package com.lnu.src.manager;

import com.lnu.src.model.Floor;
import com.lnu.src.thread.Elevator;
import com.lnu.src.thread.PassengerRandomizer;
import com.lnu.src.thread.Visualizer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadManager {

  private List<Floor> floors;

  public ThreadManager(List<Floor> floors) {
    this.floors = floors;
  }

  public void startPassengerRandomizer(float probability, int frequencyTime) {
    Thread passengerGenerator = new Thread(new PassengerRandomizer(probability, frequencyTime, floors),
        "Passenger_generator_thread");
    passengerGenerator.start();
  }

  public void startElevator(Elevator elevator) {
    Thread lift = new Thread(elevator, "Lift_thread_" + ThreadLocalRandom.current().nextInt());
    lift.start();
  }

  public void startVisualizer(Visualizer visualizer) {
    Thread visualizerThread = new Thread(visualizer, "Visualizer_thread");
    visualizerThread.start();
  }
}
