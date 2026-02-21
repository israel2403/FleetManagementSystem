package com.huerta.fleetmanagementsystem.model.vehicle;

public class Car extends Vehicle {

  private int seatingCapacity;

  private String powerSource;

  public Car(long id, String licensePlate, String make, String model, int year, int seatingCapacity,
      String powerSource) {
    super(id, licensePlate, make, model, year);
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
  public void calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}
