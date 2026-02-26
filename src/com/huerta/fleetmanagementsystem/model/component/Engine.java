package com.huerta.fleetmanagementsystem.model.component;

/**
 * Represents the engine component of a vehicle.
 *
 * <p><b>OOP concept – Composition:</b> an {@code Engine} is created and
 * owned by its parent {@link com.huerta.fleetmanagementsystem.model.vehicle.Vehicle}.
 * If the vehicle is destroyed, its engine ceases to exist — a defining
 * trait of <b>composition</b> (strong "has-a" relationship).</p>
 *
 * <p><b>OOP concept – Encapsulation:</b> all fields are {@code private}
 * and accessible only through validated setters, protecting object
 * invariants.</p>
 */
public class Engine {

  /** Textual description of the engine type (e.g. "V6", "Inline-4"). */
  private String engineType;

  /** Engine displacement (e.g. "2.0L"). */
  private String displacement;

  /** Engine power output (e.g. "150 HP"). */
  private String horsepower;

  /**
   * Default constructor — creates an engine with no initial values.
   */
  public Engine() {
  }

  /**
   * Parameterised constructor.
   *
   * @param engineType   the type/configuration of the engine
   * @param displacement the engine displacement
   * @param horsepower   the power output
   */
  public Engine(String engineType, String displacement, String horsepower) {
    this.engineType = engineType;
    this.displacement = displacement;
    this.horsepower = horsepower;
  }

  /**
   * Returns the engine type.
   *
   * @return engine type description, or {@code null} if not set
   */
  public String getEngineType() {
    return engineType;
  }

  /**
   * Returns the displacement.
   *
   * @return displacement string, or {@code null} if not set
   */
  public String getDisplacement() {
    return displacement;
  }

  /**
   * Returns the horsepower.
   *
   * @return horsepower string, or {@code null} if not set
   */
  public String getHorsepower() {
    return horsepower;
  }

  /**
   * Sets the engine type.
   *
   * @param engineType non-{@code null}, non-blank engine type
   * @throws IllegalArgumentException if {@code engineType} is null or blank
   */
  public void setEngineType(String engineType) {
    if (engineType == null || engineType.isBlank()) {
      throw new IllegalArgumentException("Engine type cannot be null or empty.");
    }
    this.engineType = engineType;
  }

  /**
   * Sets the displacement.
   *
   * @param displacement non-{@code null}, non-blank displacement
   * @throws IllegalArgumentException if {@code displacement} is null or blank
   */
  public void setDisplacement(String displacement) {
    if (displacement == null || displacement.isBlank()) {
      throw new IllegalArgumentException("Displacement cannot be null or empty.");
    }
    this.displacement = displacement;
  }

  /**
   * Sets the horsepower.
   *
   * @param horsepower non-{@code null}, non-blank horsepower
   * @throws IllegalArgumentException if {@code horsepower} is null or blank
   */
  public void setHorsepower(String horsepower) {
    if (horsepower == null || horsepower.isBlank()) {
      throw new IllegalArgumentException("Horsepower cannot be null or empty.");
    }
    this.horsepower = horsepower;
  }
}
