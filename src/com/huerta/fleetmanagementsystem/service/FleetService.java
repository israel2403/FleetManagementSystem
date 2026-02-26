package com.huerta.fleetmanagementsystem.service;

import java.util.List;

import com.huerta.fleetmanagementsystem.model.fleet.Fleet;
import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;
import com.huerta.fleetmanagementsystem.model.person.Driver;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;

/**
 * Service façade that mediates between the GUI layer and the
 * {@link Fleet} domain model.
 *
 * <p><b>OOP concept – Modularity:</b> the GUI panels never access
 * the {@code Fleet} directly; they depend only on this service,
 * keeping presentation and domain logic separated.</p>
 *
 * <p><b>OOP concept – Aggregation:</b> {@code FleetService} holds a
 * reference to a {@link Fleet} but does not create it; the fleet can
 * outlive the service (weak "has-a").</p>
 *
 * @param fleet the {@link Fleet} managed by this service
 */
public record FleetService(Fleet fleet) {

  /**
   * Returns the read-only list of vehicles in the fleet.
   *
   * @return unmodifiable vehicle list
   */
  public List<Vehicle> vehicles() {
    return fleet.getVehicles();
  }

  /**
   * Returns the read-only list of drivers in the fleet.
   *
   * @return unmodifiable driver list
   */
  public List<Driver> drivers() {
    return fleet.getDrivers();
  }

  /** @param v vehicle to add to the fleet */
  public void addVehicle(Vehicle v) {
    fleet.addVehicle(v);
  }

  /** @param d driver to add to the fleet */
  public void addDriver(Driver d) {
    fleet.addDriver(d);
  }

  /**
   * Assigns a driver to a vehicle.
   *
   * @param v the target vehicle
   * @param d the driver to assign
   */
  public void assignDriver(Vehicle v, Driver d) {
    v.assignDriver(d);
  }

  /**
   * Registers a maintenance record against a vehicle.
   *
   * @param v the vehicle
   * @param r the maintenance record
   */
  public void addMaintenance(Vehicle v, MaintenanceRecord r) {
    v.registerMaintenance(r);
  }

  /** Delegates to {@link Fleet#generateFleetReports()} (<b>polymorphism</b>). */
  public List<String> generateFleetReports() {
    return fleet.generateFleetReports();
  }

  /** Delegates to {@link Fleet#getVehicleSpecificDetails()} (<b>downcasting</b>). */
  public List<String> getVehicleSpecificDetails() {
    return fleet.getVehicleSpecificDetails();
  }

  /** Delegates to {@link Fleet#getVehiclesRequiringCommercialLicense()} (<b>downcasting</b>). */
  public List<Vehicle> getVehiclesRequiringCommercialLicense() {
    return fleet.getVehiclesRequiringCommercialLicense();
  }

  /**
   * Calculates the total operating cost across all vehicles.
   *
   * <p><b>Polymorphism:</b> each vehicle’s concrete
   * {@code calculateOperatingCost()} is invoked at runtime.</p>
   *
   * @return sum of all vehicle operating costs
   */
  public double totalOperatingCost() {
    double total = 0.0;
    for (Vehicle v : vehicles()) {
      total += v.calculateOperatingCost(); // polymorphism
    }
    return total;
  }
}
