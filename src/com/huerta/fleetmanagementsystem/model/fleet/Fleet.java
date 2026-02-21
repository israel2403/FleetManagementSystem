package com.huerta.fleetmanagementsystem.model.fleet;

import java.util.List;

import com.huerta.fleetmanagementsystem.model.person.Driver;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;

public class Fleet {
  private List<Vehicle> vehicles;

  private List<Driver> drivers;

  public Fleet(List<Vehicle> vehicles, List<Driver> drivers) {
    this.vehicles = vehicles;
    this.drivers = drivers;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public List<Driver> getDrivers() {
    return drivers;
  }

  public void setDrivers(List<Driver> drivers) {
    this.drivers = drivers;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }
}
