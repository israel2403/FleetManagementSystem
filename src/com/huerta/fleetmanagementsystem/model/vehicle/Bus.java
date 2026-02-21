package com.huerta.fleetmanagementsystem.model.vehicle;

import com.huerta.fleetmanagementsystem.model.enums.ServiceType;

public class Bus extends Vehicle {

  private int passengerCapacity;

  private ServiceType serviceType;

  public Bus(long id, String licensePlate, String make, String model, int year, int passengerCapacity,
      ServiceType serviceType) {
    super(id, licensePlate, make, model, year);
    this.passengerCapacity = passengerCapacity;
    this.serviceType = serviceType;
  }

  public int getPassengerCapacity() {
    return passengerCapacity;
  }

  public void setPassengerCapacity(int passengerCapacity) {
    this.passengerCapacity = passengerCapacity;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  @Override
  public void calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}
