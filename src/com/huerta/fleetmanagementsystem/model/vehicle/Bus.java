package com.huerta.fleetmanagementsystem.model.vehicle;

import com.huerta.fleetmanagementsystem.model.enums.ServiceType;

/**
 * Concrete vehicle representing a passenger bus.
 *
 * <p><b>OOP concept – Hierarchy:</b> {@code Bus} extends
 * {@link HeavyVehicle} → {@link Vehicle} (three-level chain).</p>
 *
 * <p><b>OOP concept – Polymorphism:</b> overrides
 * {@link #calculateOperatingCost()} with a bus-specific formula
 * that accounts for passenger capacity and service type.</p>
 *
 * @see ServiceType
 */
public class Bus extends HeavyVehicle {

  /** Maximum passenger capacity (≥ 1). */
  private int passengerCapacity;

  /** Route service type (city or intercity). */
  private ServiceType serviceType;

  /**
   * Creates a new bus.
   *
   * @param id                 unique identifier
   * @param licensePlate       licence plate
   * @param make               manufacturer
   * @param model              model name
   * @param year               manufacturing year
   * @param passengerCapacity  max passengers (≥ 1)
   * @param serviceType        service route type; must not be {@code null}
   * @param mileage            total kilometres
   * @param grossVehicleWeight gross weight in tons
   * @throws IllegalArgumentException if any argument is invalid
   */
  public Bus(long id, String licensePlate, String make, String model, int year, int passengerCapacity,
      ServiceType serviceType, double mileage, double grossVehicleWeight) {
    super(id, licensePlate, make, model, year, mileage, grossVehicleWeight);
    setPassengerCapacity(passengerCapacity);
    setServiceType(serviceType);
  }

  /** @return passenger capacity */
  public int getPassengerCapacity() {
    return passengerCapacity;
  }

  /**
   * @param passengerCapacity must be ≥ 1
   * @throws IllegalArgumentException if less than 1
   */
  public void setPassengerCapacity(int passengerCapacity) {
    if (passengerCapacity < 1) {
      throw new IllegalArgumentException("Passenger capacity must be at least 1.");
    }
    this.passengerCapacity = passengerCapacity;
  }

  /** @return service type */
  public ServiceType getServiceType() {
    return serviceType;
  }

  /**
   * @param serviceType non-{@code null}
   * @throws IllegalArgumentException if {@code null}
   */
  public void setServiceType(ServiceType serviceType) {
    if (serviceType == null) {
      throw new IllegalArgumentException("Service type cannot be null.");
    }
    this.serviceType = serviceType;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Bus formula: base(150) + mileage×0.025 + passengers×4 + service-type factor.</p>
   */
  @Override
  public double calculateOperatingCost() {

    double baseCost = 150.0;

    double mileageFactor = getMileage() * 0.025;

    double passengerFactor = this.passengerCapacity * 4.0;

    double serviceFactor;
    if (this.serviceType != null) {
      switch (this.serviceType) {
        case CITY:
          serviceFactor = 40.0;
          break;
        case INTERCITY:
          serviceFactor = 70.0;
          break;
        default:
          serviceFactor = 50.0;
      }
    } else {
      serviceFactor = 50.0;
    }

    return baseCost + mileageFactor + passengerFactor + serviceFactor;
  }
}
