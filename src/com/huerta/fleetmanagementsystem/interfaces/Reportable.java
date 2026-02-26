package com.huerta.fleetmanagementsystem.interfaces;

/**
 * Defines the contract for objects that can produce a human-readable report.
 *
 * <p><b>OOP concept – Abstraction &amp; Polymorphism:</b> any class that
 * implements this interface can be treated as a {@code Reportable}
 * reference, and its concrete {@link #generateReport()} implementation
 * will be invoked at runtime via <b>dynamic dispatch</b>.</p>
 */
public interface Reportable {

  /**
   * Generates a formatted, multi-line textual report for this object.
   *
   * @return a non-{@code null} report string
   */
  String generateReport();
}
