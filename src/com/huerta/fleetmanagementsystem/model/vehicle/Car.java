package com.huerta.fleetmanagementsystem.model.vehicle;

import com.huerta.fleetmanagementsystem.model.enums.FuelType;

/**
 * Concrete vehicle representing a passenger car.
 *
 * <p><b>OOP concept – Hierarchy:</b> {@code Car} extends {@link Vehicle}
 * directly (single-level inheritance) and adds car-specific attributes
 * such as seating capacity and fuel type.</p>
 *
 * <p><b>OOP concept – Polymorphism:</b> overrides
 * {@link #calculateOperatingCost()} with a formula that factors in
 * mileage, seating and fuel type.</p>
 *
 * <p><b>OOP concept – Upcasting:</b> a {@code Car} instance can be
 * stored as a {@code Vehicle} reference (implicit upcasting), enabling
 * polymorphic iteration.</p>
 *
 * @see FuelType
 */
public class Car extends Vehicle {

  /** Number of seats (≥ 1). */
  private int seatingCapacity;

  /** Fuel / energy source. */
  private FuelType fuelType;

  /**
   * Creates a new car.
   *
   * @param id              unique identifier
   * @param licensePlate    licence plate
   * @param make            manufacturer
   * @param model           model name
   * @param year            manufacturing year
   * @param seatingCapacity number of seats (≥ 1)
   * @param fuelType        fuel/energy type; must not be {@code null}
   * @param mileage         total kilometres
   * @throws IllegalArgumentException if any argument is invalid
   */
  public Car(long id, String licensePlate, String make, String model, int year, int seatingCapacity,
      FuelType fuelType, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
    setSeatingCapacity(seatingCapacity);
    setFuelType(fuelType);
  }

  /**
   * Returns the seating capacity.
   *
   * @return number of seats
   */
  public int getSeatingCapacity() {
    return seatingCapacity;
  }

  /**
   * Sets the seating capacity.
   *
   * @param seatingCapacity must be ≥ 1
   * @throws IllegalArgumentException if less than 1
   */
  public void setSeatingCapacity(int seatingCapacity) {
    if (seatingCapacity < 1) {
      throw new IllegalArgumentException("Seating capacity must be at least 1.");
    }
    this.seatingCapacity = seatingCapacity;
  }

  /**
   * Returns the fuel type.
   *
   * @return {@link FuelType}
   */
  public FuelType getFuelType() {
    return fuelType;
  }

  /**
   * Sets the fuel type.
   *
   * @param fuelType non-{@code null} fuel type
   * @throws IllegalArgumentException if {@code null}
   */
  public void setFuelType(FuelType fuelType) {
    if (fuelType == null) {
      throw new IllegalArgumentException("Fuel type cannot be null.");
    }
    this.fuelType = fuelType;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Car formula: base(100) + mileage×0.02 + seats×5 + fuel-type factor.</p>
   */
  @Override
  public double calculateOperatingCost() {

    double baseCost = 100.0;

    double mileageFactor = getMileage() * 0.02;

    double seatingFactor = this.seatingCapacity * 5.0;

    double powerFactor;
    switch (fuelType) {
      case ELECTRIC:
        powerFactor = 10.0;
        break;
      case DIESEL:
        powerFactor = 25.0;
        break;
      case GASOLINE:
        powerFactor = 20.0;
        break;
      case HYBRID:
        powerFactor = 15.0;
        break;
      default:
        powerFactor = 15.0;
    }

    return baseCost + mileageFactor + seatingFactor + powerFactor;
  }
}
