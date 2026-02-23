package com.huerta.fleetmanagementsystem.model.vehicle;

public class Motorcycle extends Vehicle {

  private int engineDisplacement;

  public Motorcycle(long id, String licensePlate, String make, String model, int year,
      int engineDisplacement, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
    this.engineDisplacement = engineDisplacement;
  }

  public int getEngineDisplacement() {
    return engineDisplacement;
  }

  public void setEngineDisplacement(int engineDisplacement) {
    this.engineDisplacement = engineDisplacement;
  }

  @Override
  public double calculateOperatingCost() {

    double baseCost = 60.0;

    double mileageFactor = getMileage() * 0.015;

    double displacementFactor = this.engineDisplacement * 0.05;

    return baseCost + mileageFactor + displacementFactor;
  }
}
