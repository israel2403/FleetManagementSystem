error id: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Truck.java:java/lang/Override#
file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Truck.java
empty definition using pc, found symbol in pc: java/lang/Override#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 777
uri: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Truck.java
text:
```scala
package com.huerta.fleetmanagementsystem.model.vehicle;

public class Truck extends Vehicle {

  private int payloadCapacity;

  private int axleCount;

  private double mileage;

  public Truck(long id, String licensePlate, String make, String model, int year, int payloadCapacity,
      int axleCount, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
    this.payloadCapacity = payloadCapacity;
    this.axleCount = axleCount;
  }

  public int getPayloadCapacity() {
    return payloadCapacity;
  }

  public void setPayloadCapacity(int payloadCapacity) {
    this.payloadCapacity = payloadCapacity;
  }

  public int getAxleCount() {
    return axleCount;
  }

  public void setAxleCount(int axleCount) {
    this.axleCount = axleCount;
  }

  @Ov@@erride
  public double calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/Override#