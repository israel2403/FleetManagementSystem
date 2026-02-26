package com.huerta.fleetmanagementsystem.model.enums;

/**
 * Enumerates the supported fuel/energy types for vehicles.
 *
 * <p>Used by {@link com.huerta.fleetmanagementsystem.model.vehicle.Car}
 * to influence the operating-cost calculation (<b>polymorphism</b>).</p>
 */
public enum FuelType {
  /** Conventional petrol engine. */
  GASOLINE,
  /** Diesel combustion engine. */
  DIESEL,
  /** Fully electric powertrain. */
  ELECTRIC,
  /** Combined combustion + electric powertrain. */
  HYBRID
}
