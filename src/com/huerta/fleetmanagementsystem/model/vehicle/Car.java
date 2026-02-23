package com.huerta.fleetmanagementsystem.model.vehicle;

public class Car extends Vehicle {

  private int seatingCapacity;

  private String powerSource;

  public Car(long id, String licensePlate, String make, String model, int year, int seatingCapacity,
      String powerSource, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
    this.seatingCapacity = seatingCapacity;
    this.powerSource = powerSource;
  }

  public int getSeatingCapacity() {
    return seatingCapacity;
  }

  public void setSeatingCapacity(int seatingCapacity) {
    this.seatingCapacity = seatingCapacity;
  }

  public String getPowerSource() {
    return powerSource;
  }

  public void setPowerSource(String powerSource) {
    this.powerSource = powerSource;
  }

  @Override
  public double calculateOperatingCost() {

    double baseCost = 100.0;

    double mileageFactor = getMileage() * 0.02;

    double seatingFactor = this.seatingCapacity * 5.0;

    double powerFactor;
    if (powerSource != null) {
      switch (powerSource.trim().toUpperCase()) {
        case "ELECTRIC":
          powerFactor = 10.0;
          break;
        case "DIESEL":
          powerFactor = 25.0;
          break;
        case "GASOLINE":
          powerFactor = 20.0;
          break;
        default:
          powerFactor = 15.0;
      }
    } else {
      powerFactor = 15.0;
    }

    return baseCost + mileageFactor + seatingFactor + powerFactor;
  }
}
