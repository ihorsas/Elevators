package com.lnu.src.model;

import java.util.concurrent.LinkedBlockingQueue;

public class Floor {

  private final LinkedBlockingQueue<Passenger> listPassengersUp = new LinkedBlockingQueue<>();
  private final LinkedBlockingQueue<Passenger> listPassengersDown = new LinkedBlockingQueue<>();


  public synchronized LinkedBlockingQueue<Passenger> getListPassengersDown() {
    return listPassengersDown;
  }

  public synchronized LinkedBlockingQueue<Passenger> getListPassengersUp() {
    return listPassengersUp;
  }

  public synchronized boolean hasPassengers() {
    return listPassengersUp.size() != 0 || listPassengersDown.size() != 0;
  }

  public synchronized Passenger getPassengerUp() {
    return listPassengersUp.poll();
  }

  public synchronized Passenger getPassengerDown() {
    return listPassengersDown.poll();
  }


  public void addPassenger(Passenger passenger) throws InterruptedException {
    if (passenger.isGoingUp()) {
      listPassengersUp.put(passenger);
    } else {
      listPassengersDown.put(passenger);
    }
  }

  public boolean isEmpty() {
    return listPassengersUp.isEmpty() && listPassengersDown.isEmpty();
  }

  public String toString() {
    String s = listPassengersUp.toString();
    s += listPassengersDown.toString();
    return s;
  }
}
