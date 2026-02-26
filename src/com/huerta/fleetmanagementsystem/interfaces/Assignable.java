package com.huerta.fleetmanagementsystem.interfaces;

import com.huerta.fleetmanagementsystem.model.person.Driver;

/**
 * Defines the contract for objects that can be assigned to a {@link Driver}.
 *
 * <p><b>OOP concept – Abstraction:</b> this interface abstracts the
 * assignment behaviour away from any concrete vehicle type, allowing any
 * implementing class to be treated uniformly through the {@code Assignable}
 * reference (<b>polymorphism</b>).</p>
 *
 * @see Driver
 */
public interface Assignable {

  /**
   * Assigns a driver to this object.
   *
   * @param driver the {@link Driver} to assign; must not be {@code null}
   */
  void assignDriver(Driver driver);

  /**
   * Releases the currently assigned driver, if any.
   */
  void releaseDriver();
}
