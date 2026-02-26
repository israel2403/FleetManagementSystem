package com.huerta.fleetmanagementsystem.model.vehicle;

/**
 * Concrete vehicle representing a motorcycle.
 *
 * <p><b>OOP concept – Hierarchy:</b> {@code Motorcycle} extends
 * {@link Vehicle} directly (single-level inheritance).</p>
 *
 * <p><b>OOP concept – Polymorphism:</b> overrides
 * {@link #calculateOperatingCost()} with a lightweight formula
 * based on displacement.</p>
 */
public class Motorcycle extends Vehicle {

  /** Engine displacement in cubic centimetres (≥ 1). */
  private int engineDisplacement;

  /**
   * Creates a new motorcycle.
   *
   * @param id                 unique identifier
   * @param licensePlate       licence plate
   * @param make               manufacturer
   * @param model              model name
   * @param year               manufacturing year
   * @param engineDisplacement displacement in cc (≥ 1)
   * @param mileage            total kilometres
   * @throws IllegalArgumentException if any argument is invalid
   */
  public Motorcycle(long id, String licensePlate, String make, String model, int year,
      int engineDisplacement, double mileage) {
    super(id, licensePlate, make, model, year, mileage);
    setEngineDisplacement(engineDisplacement);
  }

  /** @return engine displacement in cc */
  public int getEngineDisplacement() {
    return engineDisplacement;
  }

  /**
   * @param engineDisplacement must be ≥ 1
   * @throws IllegalArgumentException if less than 1
   */
  public void setEngineDisplacement(int engineDisplacement) {
    if (engineDisplacement < 1) {
      throw new IllegalArgumentException("Engine displacement must be at least 1.");
    }
    this.engineDisplacement = engineDisplacement;
  }

  /**
   * {@inheritDoc}
   *
   * <p>Motorcycle formula: base(60) + mileage×0.015 + displacement×0.05.</p>
   */
  @Override
  public double calculateOperatingCost() {

    double baseCost = 60.0;

    double mileageFactor = getMileage() * 0.015;

    double displacementFactor = this.engineDisplacement * 0.05;

    return baseCost + mileageFactor + displacementFactor;
  }
}
