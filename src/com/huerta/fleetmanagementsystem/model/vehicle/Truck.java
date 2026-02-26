package com.huerta.fleetmanagementsystem.model.vehicle;

/**
 * Concrete vehicle representing a freight truck.
 *
 * <p><b>OOP concept – Hierarchy:</b> {@code Truck} extends
 * {@link HeavyVehicle}, which in turn extends {@link Vehicle}
 * (three-level inheritance chain).</p>
 *
 * <p><b>OOP concept – Polymorphism:</b> overrides
 * {@link #calculateOperatingCost()} with a truck-specific formula
 * that accounts for payload and axle count.</p>
 *
 * <p><b>OOP concept – Downcasting:</b> in
 * {@link com.huerta.fleetmanagementsystem.model.fleet.Fleet#getVehicleSpecificDetails()},
 * a {@code Vehicle} reference is downcast to {@code Truck} with
 * {@code instanceof} to access {@link #getPayloadCapacity()} and
 * {@link #getAxleCount()}.</p>
 */
public class Truck extends HeavyVehicle {

  /** Maximum payload in kilograms (≥ 0). */
  private int payloadCapacity;

  /** Number of axles (≥ 2). */
  private int axleCount;

  /**
   * Creates a new truck.
   *
   * @param id                 unique identifier
   * @param licensePlate       licence plate
   * @param make               manufacturer
   * @param model              model name
   * @param year               manufacturing year
   * @param payloadCapacity    max payload in kg (≥ 0)
   * @param axleCount          number of axles (≥ 2)
   * @param mileage            total kilometres
   * @param grossVehicleWeight gross weight in tons
   * @throws IllegalArgumentException if any argument is invalid
   */
  public Truck(long id, String licensePlate, String make, String model, int year, int payloadCapacity,
      int axleCount, double mileage, double grossVehicleWeight) {
    super(id, licensePlate, make, model, year, mileage, grossVehicleWeight);
    setPayloadCapacity(payloadCapacity);
    setAxleCount(axleCount);
  }

  /** @return payload capacity in kg */
  public int getPayloadCapacity() {
    return payloadCapacity;
  }

  /**
   * @param payloadCapacity must be ≥ 0
   * @throws IllegalArgumentException if negative
   */
  public void setPayloadCapacity(int payloadCapacity) {
    if (payloadCapacity < 0) {
      throw new IllegalArgumentException("Payload capacity cannot be negative.");
    }
    this.payloadCapacity = payloadCapacity;
  }

  /** @return number of axles */
  public int getAxleCount() {
    return axleCount;
  }

  /**
   * @param axleCount must be ≥ 2
   * @throws IllegalArgumentException if less than 2
   */
  public void setAxleCount(int axleCount) {
    if (axleCount < 2) {
      throw new IllegalArgumentException("Axle count must be at least 2.");
    }
    this.axleCount = axleCount;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Truck formula: base(200) + mileage×0.03 + payload×2.5 + axles×30.</p>
   */
  @Override
  public double calculateOperatingCost() {

    double baseCost = 200.0;

    double mileageFactor = getMileage() * 0.03;

    double payloadFactor = this.payloadCapacity * 2.5;

    double axleFactor = this.axleCount * 30.0;

    return baseCost + mileageFactor + payloadFactor + axleFactor;
  }
}
