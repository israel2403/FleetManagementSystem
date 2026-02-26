package com.huerta.fleetmanagementsystem.model.vehicle;

import lombok.Getter;

/**
 * Abstract intermediate class for heavy vehicles (trucks, buses).
 *
 * <p><b>OOP concept – Hierarchy (Inheritance):</b> {@code HeavyVehicle}
 * extends {@link Vehicle} adding weight-related attributes, and is further
 * specialised by {@link Truck} and {@link Bus}. This creates a three-level
 * inheritance chain: {@code Vehicle → HeavyVehicle → Truck/Bus}.</p>
 *
 * <p><b>OOP concept – Upcasting:</b> a {@code Truck} or {@code Bus}
 * can be referenced as a {@code HeavyVehicle} or {@code Vehicle},
 * allowing polymorphic behaviour.</p>
 *
 * <p><b>OOP concept – Downcasting:</b>
 * {@link com.huerta.fleetmanagementsystem.model.fleet.Fleet#getVehiclesRequiringCommercialLicense()}
 * downcasts {@code Vehicle} references to {@code HeavyVehicle} using
 * {@code instanceof} to access {@link #requiresCommercialLicense()}.</p>
 *
 * @see Truck
 * @see Bus
 */
@Getter
public abstract class HeavyVehicle extends Vehicle {

  /** Gross vehicle weight in metric tons (≥ 0). */
  private double grossVehicleWeight;

  /**
   * Creates a heavy vehicle.
   *
   * @param id                  unique identifier
   * @param licensePlate        licence plate
   * @param make                manufacturer
   * @param model               model name
   * @param year                manufacturing year
   * @param mileage             total kilometres
   * @param grossVehicleWeight  gross weight in tons (≥ 0)
   * @throws IllegalArgumentException if any argument is invalid
   */
  public HeavyVehicle(long id, String licensePlate, String make, String model, int year,
      double mileage, double grossVehicleWeight) {
    super(id, licensePlate, make, model, year, mileage);
    setGrossVehicleWeight(grossVehicleWeight);
  }

  /**
   * Sets the gross vehicle weight.
   *
   * @param grossVehicleWeight weight in tons; must be ≥ 0
   * @throws IllegalArgumentException if negative
   */
    public void setGrossVehicleWeight(double grossVehicleWeight) {
    if (grossVehicleWeight < 0) {
      throw new IllegalArgumentException("Gross vehicle weight cannot be negative.");
    }
    this.grossVehicleWeight = grossVehicleWeight;
  }

  /**
   * Determines whether this vehicle exceeds the 3.5-ton threshold
   * that requires a commercial driving licence.
   *
   * @return {@code true} if {@code grossVehicleWeight > 3.5}
   */
  public boolean requiresCommercialLicense() {
    return grossVehicleWeight > 3.5;
  }
}
