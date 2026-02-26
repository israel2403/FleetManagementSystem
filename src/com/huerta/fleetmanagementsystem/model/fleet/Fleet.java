package com.huerta.fleetmanagementsystem.model.fleet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;
import com.huerta.fleetmanagementsystem.model.person.Driver;
import com.huerta.fleetmanagementsystem.model.vehicle.Bus;
import com.huerta.fleetmanagementsystem.model.vehicle.Car;
import com.huerta.fleetmanagementsystem.model.vehicle.HeavyVehicle;
import com.huerta.fleetmanagementsystem.model.vehicle.Motorcycle;
import com.huerta.fleetmanagementsystem.model.vehicle.Truck;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;

/**
 * Central domain model that manages a collection of vehicles and drivers.
 *
 * <p><b>OOP concept – Aggregation:</b> {@code Fleet} holds lists of
 * {@link Vehicle} and {@link Driver} objects, but neither is created by
 * the fleet itself — they can exist independently. This is a weak "has-a"
 * relationship (aggregation, ◇).</p>
 *
 * <p><b>OOP concept – Polymorphism:</b>
 * {@link #calculateTotalOperatingCost()} and {@link #generateFleetReports()}
 * iterate over {@code Vehicle} references; the concrete subclass method is
 * selected at runtime via <b>dynamic dispatch</b>.</p>
 *
 * <p><b>OOP concept – Downcasting:</b>
 * {@link #getVehicleSpecificDetails()} uses {@code instanceof} checks
 * followed by explicit casts to access subclass-specific getters
 * (e.g. {@code Truck.getPayloadCapacity()}) that are not available
 * through the {@code Vehicle} reference.</p>
 *
 * <p><b>OOP concept – Upcasting:</b> when a {@code Car}, {@code Truck},
 * {@code Bus} or {@code Motorcycle} is added via {@link #addVehicle(Vehicle)},
 * it is implicitly upcast to {@code Vehicle} and stored in the generic
 * list.</p>
 *
 * <p><b>OOP concept – Encapsulation:</b> the internal lists are
 * {@code private final} and exposed only as unmodifiable views.</p>
 */
public class Fleet {

  /** Vehicles belonging to this fleet (<b>aggregation</b>). */
  private final List<Vehicle> vehicles;

  /** Drivers available in this fleet (<b>aggregation</b>). */
  private final List<Driver> drivers;

  /**
   * Creates an empty fleet.
   */
  public Fleet() {
    this.vehicles = new ArrayList<>();
    this.drivers = new ArrayList<>();
  }

  /**
   * Creates a fleet pre-loaded with the given vehicles and drivers.
   *
   * @param vehicles initial vehicles; {@code null} yields an empty list
   * @param drivers  initial drivers; {@code null} yields an empty list
   */
  public Fleet(List<Vehicle> vehicles, List<Driver> drivers) {
    this.vehicles = (vehicles == null) ? new ArrayList<>() : new ArrayList<>(vehicles);
    this.drivers = (drivers == null) ? new ArrayList<>() : new ArrayList<>(drivers);
  }

  /**
   * Returns an unmodifiable view of the vehicle list (<b>encapsulation</b>).
   *
   * @return read-only list of vehicles
   */
  public List<Vehicle> getVehicles() {
    return Collections.unmodifiableList(vehicles);
  }

  /**
   * Returns an unmodifiable view of the driver list (<b>encapsulation</b>).
   *
   * @return read-only list of drivers
   */
  public List<Driver> getDrivers() {
    return Collections.unmodifiableList(drivers);
  }

  // ---- CRUD-like operations ----

  /**
   * Adds a vehicle to the fleet (<b>upcasting</b> — any subclass is
   * accepted as a {@code Vehicle} reference).
   *
   * @param vehicle vehicle to add; ignored if {@code null}
   */
  public void addVehicle(Vehicle vehicle) {
    if (vehicle != null) {
      vehicles.add(vehicle);
    }
  }

  /**
   * Removes a vehicle from the fleet.
   *
   * @param vehicle vehicle to remove
   * @return {@code true} if the vehicle was found and removed
   */
  public boolean removeVehicle(Vehicle vehicle) {
    return vehicles.remove(vehicle);
  }

  /**
   * Adds a driver to the fleet.
   *
   * @param driver driver to add; ignored if {@code null}
   */
  public void addDriver(Driver driver) {
    if (driver != null) {
      drivers.add(driver);
    }
  }

  /**
   * Removes a driver from the fleet. If the driver is currently assigned
   * to any vehicle, the assignment is released first.
   *
   * @param driver driver to remove
   * @return {@code true} if the driver was found and removed
   */
  public boolean removeDriver(Driver driver) {
    // Optional: if driver is assigned to any vehicle, release it
    for (Vehicle v : vehicles) {
      if (v != null && v.getDriver() == driver) {
        v.releaseDriver();
      }
    }
    return drivers.remove(driver);
  }

  // ---- Operations required by the domain ----

  /**
   * Registers a maintenance record against a vehicle.
   *
   * @param vehicle the target vehicle
   * @param record  the maintenance record to register
   */
  public void registerMaintenance(Vehicle vehicle, MaintenanceRecord record) {
    if (vehicle == null || record == null)
      return;

    if (!vehicles.contains(vehicle))
      vehicles.add(vehicle);

    vehicle.registerMaintenance(record);
  }

  // ---- Polymorphism demo helper methods ----

  /**
   * Sums the operating cost of every vehicle in the fleet.
   *
   * <p><b>Polymorphism:</b> {@code v.calculateOperatingCost()} is
   * resolved at runtime to the concrete subclass implementation.</p>
   *
   * @return total operating cost
   */
  public double calculateTotalOperatingCost() {
    double total = 0.0;
    for (Vehicle v : vehicles) {
      if (v != null) {
        total += v.calculateOperatingCost(); // dynamic dispatch
      }
    }
    return total;
  }

  /**
   * Generates a report string for every vehicle (<b>polymorphism</b>).
   *
   * @return list of formatted report strings
   */
  public List<String> generateFleetReports() {
    List<String> reports = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (v != null) {
        reports.add(v.generateReport());
      }
    }
    return reports;
  }

  /**
   * Returns subclass-specific details for every vehicle.
   *
   * <p><b>OOP concept – Downcasting:</b> each {@code Vehicle} reference
   * is tested with {@code instanceof} and explicitly cast to its
   * concrete type ({@link Truck}, {@link Bus}, {@link Car},
   * {@link Motorcycle}) so that subclass-only getters can be invoked.</p>
   *
   * @return list of detail strings (one per vehicle)
   */
  public List<String> getVehicleSpecificDetails() {
    List<String> details = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (v instanceof Truck) {
        Truck t = (Truck) v;
        details.add(String.format("Truck [%s] - Payload: %d tons, Axles: %d, Gross Weight: %.1f t",
            t.getLicensePlate(), t.getPayloadCapacity(), t.getAxleCount(), t.getGrossVehicleWeight()));
      } else if (v instanceof Bus) {
        Bus b = (Bus) v;
        details.add(String.format("Bus [%s] - Passengers: %d, Service: %s, Commercial License: %s",
            b.getLicensePlate(), b.getPassengerCapacity(), b.getServiceType(),
            b.requiresCommercialLicense() ? "Yes" : "No"));
      } else if (v instanceof Car) {
        Car c = (Car) v;
        details.add(String.format("Car [%s] - Seats: %d, Fuel: %s",
            c.getLicensePlate(), c.getSeatingCapacity(), c.getFuelType()));
      } else if (v instanceof Motorcycle) {
        Motorcycle m = (Motorcycle) v;
        details.add(String.format("Motorcycle [%s] - Displacement: %d cc",
            m.getLicensePlate(), m.getEngineDisplacement()));
      }
    }
    return details;
  }

  /**
   * Filters vehicles that require a commercial driving licence.
   *
   * <p><b>OOP concept – Downcasting:</b> {@code Vehicle} references are
   * downcast to {@link HeavyVehicle} with {@code instanceof} to call
   * {@link HeavyVehicle#requiresCommercialLicense()}.</p>
   *
   * @return list of heavy vehicles exceeding the 3.5-ton threshold
   */
  public List<Vehicle> getVehiclesRequiringCommercialLicense() {
    List<Vehicle> result = new ArrayList<>();
    for (Vehicle v : vehicles) {
      if (v instanceof HeavyVehicle) {
        HeavyVehicle hv = (HeavyVehicle) v;
        if (hv.requiresCommercialLicense()) {
          result.add(v);
        }
      }
    }
    return result;
  }
}
