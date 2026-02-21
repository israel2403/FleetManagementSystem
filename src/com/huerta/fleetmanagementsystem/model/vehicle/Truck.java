package com.huerta.fleetmanagementsystem.model.vehicle;

public class Truck extends Vehicle {

  private int payloadCapacity;

  private int axleCount;

  public Truck(long id, String licensePlate, String make, String model, int year, int payloadCapacity,
      int axleCount) {
    super(id, licensePlate, make, model, year);
    this.payloadCapacity = payloadCapacity;
    this.axleCount = axleCount;
  }

  public int getPayloadCapacity() {
    return payloadCapacity;
  }

  public void setPayloadCapacity(int payloadCapacity) {
    this.payloadCapacity = payloadCapacity;
  }

  public int getAxleCount() {
    return axleCount;
  }

  public void setAxleCount(int axleCount) {
    this.axleCount = axleCount;
  }

  @Override
  public void calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}
