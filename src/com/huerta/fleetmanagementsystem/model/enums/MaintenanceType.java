package com.huerta.fleetmanagementsystem.model.enums;

/**
 * Enumerates the categories of vehicle maintenance.
 *
 * @see com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord
 */
public enum MaintenanceType {
  /** Scheduled maintenance aimed at preventing failures. */
  PREVENTIVE,
  /** Unscheduled maintenance performed to fix an existing fault. */
  CORRECTIVE
}
