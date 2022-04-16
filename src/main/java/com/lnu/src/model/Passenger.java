package com.lnu.src.model;

import java.util.Objects;

public class Passenger {

  private int from;
  private int to;

  public Passenger(int from, int to) {
    this.from = from;
    this.to = to;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }

  public boolean isGoingUp() {
    return to > from;
  }

  public String toString() {
    return "To the floor " + to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Passenger passenger = (Passenger) o;
    return from == passenger.from &&
        to == passenger.to;
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }
}
