error id: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Vehicle.java:_empty_/Engine#
file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Vehicle.java
empty definition using pc, found symbol in pc: _empty_/Engine#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1332
uri: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Vehicle.java
text:
```scala
package com.huerta.fleetmanagementsystem.model.vehicle;

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

public abstract class Vehicle implements Assignable, Calculable, Maintainable, Reportable {

  private long id;
  private String licensePlate;
  private String make;
  private String model;
  private int year;

  private Driver driver;
  private List<MaintenanceRecord> maintenanceRecords;
  private Engine engine;
  private Transmission transmission;
  private BreakingSystem breakingSystem;

  public Vehicle() {
  }

  public Vehicle(long id, String licensePlate, String make, String model, int year) {
    this.id = id;
    this.licensePlate = licensePlate;
    this.make = make;
    this.model = model;
    this.year = year;
    this.engine = new @@Engine();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public Driver getDriver() {
    return driver;
  }

  void setDriver(Driver driver) {
    this.driver = driver;
  }

  public List<MaintenanceRecord> getMaintenanceRecords() {
    return maintenanceRecords;
  }

  public void setMaintenanceRecords(List<MaintenanceRecord> maintenanceRecords) {
    this.maintenanceRecords = maintenanceRecords;
  }

  public Engine getEngine() {
    return engine;
  }

  public Transmission getTransmission() {
    return transmission;
  }

  public BreakingSystem getBreakingSystem() {
    return breakingSystem;
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Engine#