package com.huerta.fleetmanagementsystem.model.vehicle;

import java.util.ArrayList;
import java.util.List;

import com.huerta.fleetmanagementsystem.interfaces.Assignable;
import com.huerta.fleetmanagementsystem.interfaces.Calculable;
import com.huerta.fleetmanagementsystem.interfaces.Maintainable;
import com.huerta.fleetmanagementsystem.interfaces.Reportable;
import com.huerta.fleetmanagementsystem.model.component.BreakingSystem;
import com.huerta.fleetmanagementsystem.model.component.Engine;
import com.huerta.fleetmanagementsystem.model.component.Transmission;
import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;
import com.huerta.fleetmanagementsystem.model.person.Driver;

import lombok.Getter;

@Getter
public abstract class Vehicle implements Assignable, Calculable, Maintainable, Reportable {
  private static final double MILEAGE_BLOCK_KM = 10000.0;
  private static final double DEPRECIATION_PER_MILEAGE_BLOCK = 0.01;

  private long id;
  private String licensePlate;
  private String make;
  private String model;
  private int year;
  private double mileage;
  private double purchasePrice;

  private Driver driver;
  private List<MaintenanceRecord> maintenanceRecords;
  private Engine engine;
  private Transmission transmission;
  private BreakingSystem breakingSystem;

  public Vehicle() {
  }

  public Vehicle(long id, String licensePlate, String make, String model, int year, double mileage) {
    this.id = id;
    this.licensePlate = licensePlate;
    this.make = make;
    this.model = model;
    this.year = year;
    this.mileage = mileage;
    this.engine = new Engine();
    this.transmission = new Transmission();
    this.breakingSystem = new BreakingSystem();
    this.maintenanceRecords = new ArrayList<>();
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public void registerMaintenance(MaintenanceRecord maintenanceRecord) {
    if (maintenanceRecord != null) {
      this.maintenanceRecords.add(maintenanceRecord);
    }
  }

  @Override
  public String generateReport() {
    String driverName = "No Driver";
    if (this.driver != null && this.driver.getFullName() != null && !this.driver.getFullName().isBlank()) {
      driverName = this.driver.getFullName();
    }

    return String.format(
        "Vehicle Report%nLicense Plate: %s%nMake: %s%nModel: %s%nYear: %d%nDriver: %s",
        this.licensePlate,
        this.make,
        this.model,
        this.year,
        driverName);
  }

  @Override
  public void assignDriver(Driver driver) {
    if (driver != null) {
      this.driver = driver;
    }

  }

  @Override
  public void releaseDriver() {
    if (this.driver == null) {
      return;
    }
    this.driver = null;

  }

  @Override
  public double calculateDepreciation() {
    // based depreciation 10% per year
    // plus 1% per 10,000 km

    int currentYear = java.time.Year.now().getValue();
    int age = currentYear - this.year;

    double ageDepreciation = age * 0.10; // 10% per year
    double mileageDepreciation = (this.mileage / MILEAGE_BLOCK_KM) * DEPRECIATION_PER_MILEAGE_BLOCK; // 1% per 10k km

    double totalDepreciation = ageDepreciation + mileageDepreciation;

    // Cap at 90%
    return Math.min(totalDepreciation, 0.90);
  }

  public abstract double calculateOperatingCost();
}
