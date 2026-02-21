package com.huerta.fleetmanagementsystem.model.vehicle;

public class Motorcycle extends Vehicle {

  private int engineDisplacement;

  public Motorcycle(long id, String licensePlate, String make, String model, int year,
      int engineDisplacement) {
    super(id, licensePlate, make, model, year);
    this.engineDisplacement = engineDisplacement;
  }

  public int getEngineDisplacement() {
    return engineDisplacement;
  }

  public void setEngineDisplacement(int engineDisplacement) {
    this.engineDisplacement = engineDisplacement;
  }

  @Override
  public void calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}
