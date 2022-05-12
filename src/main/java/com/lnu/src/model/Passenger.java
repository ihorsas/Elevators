package com.lnu.src.model;

import java.util.Objects;

public class Passenger {

  private int from;
  private int to;

  private Passenger(Builder builder) {
    this.from = builder.from;
    this.to = builder.to;
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

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Passenger{");
    sb.append("from=").append(from);
    sb.append(", to=").append(to);
    sb.append(", hashcode=").append(hashCode());
    sb.append('}');
    return sb.toString();
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

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private int from;
    private int to;

    public Builder from(int from) {
      this.from = from;
      return this;
    }

    public Builder to(int to) {
      this.to = to;
      return this;
    }

    public Passenger build() {
      return new Passenger(this);
    }
  }
}
