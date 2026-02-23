error id: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Bus.java:java/lang/Override#
file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Bus.java
empty definition using pc, found symbol in pc: java/lang/Override#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 883
uri: file://<HOME>/Proyectos/master/tech%20web/FleetManagementSystem/src/com/huerta/fleetmanagementsystem/model/vehicle/Bus.java
text:
```scala
package com.huerta.fleetmanagementsystem.model.vehicle;

import com.huerta.fleetmanagementsystem.model.enums.ServiceType;

public class Bus extends Vehicle {

  private int passengerCapacity;

  private ServiceType serviceType;

  public Bus(long id, String licensePlate, String make, String model, int year, int passengerCapacity,
      ServiceType serviceType, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
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

  @O@@verride
  public double calculateOperatingCost() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateOperatingCost'");
  }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/Override#