error id: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Motorcycle.java:java/lang/Override#
file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Motorcycle.java
empty definition using pc, found symbol in pc: java/lang/Override#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 604
uri: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Motorcycle.java
text:
```scala
package com.huerta.fleetmanagementsystem.model.vehicle;

public class Motorcycle extends Vehicle {

  private int engineDisplacement;

  private double mileage;

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

  @@@Override
  public double calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/Override#