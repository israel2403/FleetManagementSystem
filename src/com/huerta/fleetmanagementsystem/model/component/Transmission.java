package com.huerta.fleetmanagementsystem.model.component;

import com.huerta.fleetmanagementsystem.model.enums.TransmissionType;

/**
 * Represents the transmission component of a vehicle.
 *
 * <p><b>OOP concept – Composition:</b> a {@code Transmission} is created
 * and owned by its parent {@link com.huerta.fleetmanagementsystem.model.vehicle.Vehicle};
 * it cannot exist independently.</p>
 *
 * <p><b>OOP concept – Encapsulation:</b> fields are {@code private} with
 * validated setters that enforce business rules (e.g. minimum one gear).</p>
 *
 * @see TransmissionType
 */
public class Transmission {

  /** Number of gears in this transmission (≥ 1). */
  private int numberOfGears;

  /** The shift type (manual or automatic). */
  private TransmissionType transmissionType;

  /**
   * Default constructor — creates a transmission with no initial values.
   */
  public Transmission() {
  }

  /**
   * Parameterised constructor.
   *
   * @param numberOfGears    the number of gears (≥ 1)
   * @param transmissionType the shift type
   */
  public Transmission(int numberOfGears, TransmissionType transmissionType) {
    this.numberOfGears = numberOfGears;
    this.transmissionType = transmissionType;
  }

  /**
   * Returns the number of gears.
   *
   * @return gear count
   */
  public int getNumberOfGears() {
    return numberOfGears;
  }

  /**
   * Sets the number of gears.
   *
   * @param numberOfGears must be ≥ 1
   * @throws IllegalArgumentException if less than 1
   */
  public void setNumberOfGears(int numberOfGears) {
    if (numberOfGears < 1) {
      throw new IllegalArgumentException("Number of gears must be at least 1.");
    }
    this.numberOfGears = numberOfGears;
  }

  /**
   * Returns the transmission type.
   *
   * @return the {@link TransmissionType}, or {@code null} if not set
   */
  public TransmissionType getTransmissionType() {
    return transmissionType;
  }

  /**
   * Sets the transmission type.
   *
   * @param transmissionType non-{@code null} type
   * @throws IllegalArgumentException if {@code null}
   */
  public void setTransmissionType(TransmissionType transmissionType) {
    if (transmissionType == null) {
      throw new IllegalArgumentException("Transmission type cannot be null.");
    }
    this.transmissionType = transmissionType;
  }
}
