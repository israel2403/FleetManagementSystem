package com.huerta.fleetmanagementsystem.model.vehicle;

public class Truck extends Vehicle {

  private int payloadCapacity;

  private int axleCount;

  public Truck(long id, String licensePlate, String make, String model, int year, int payloadCapacity,
      int axleCount, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
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
  public double calculateOperatingCost() {

    double baseCost = 200.0;

    double mileageFactor = getMileage() * 0.03;

    double payloadFactor = this.payloadCapacity * 2.5;

    double axleFactor = this.axleCount * 30.0;

    return baseCost + mileageFactor + payloadFactor + axleFactor;
  }
}
