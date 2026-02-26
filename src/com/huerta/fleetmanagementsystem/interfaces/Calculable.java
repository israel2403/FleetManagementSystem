package com.huerta.fleetmanagementsystem.interfaces;

/**
 * Defines the contract for objects that support financial calculations.
 *
 * <p><b>OOP concept – Abstraction &amp; Polymorphism:</b> each concrete
 * vehicle subclass provides its own implementation of these methods,
 * enabling <b>polymorphic dispatch</b> when iterating over a collection
 * of {@code Calculable} references.</p>
 */
public interface Calculable {

  /**
   * Calculates the depreciation factor of this object based on age and usage.
   *
   * @return a value between {@code 0.0} (no depreciation) and {@code 0.90}
   *         (maximum depreciation cap)
   */
  double calculateDepreciation();

  /**
   * Calculates the estimated monthly operating cost.
   *
   * <p>Each subclass overrides this method to account for type-specific
   * factors (<b>polymorphism</b>).</p>
   *
   * @return the operating cost in monetary units
   */
  double calculateOperatingCost();
}
