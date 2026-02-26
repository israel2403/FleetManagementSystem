package com.huerta.fleetmanagementsystem.interfaces;

import java.util.List;

import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;

/**
 * Defines the contract for objects that track maintenance history.
 *
 * <p><b>OOP concept – Abstraction:</b> maintenance tracking is decoupled
 * from the concrete vehicle type, promoting loose coupling and
 * <b>modularity</b>.</p>
 *
 * @see MaintenanceRecord
 */
public interface Maintainable {

  /**
   * Registers a new maintenance record.
   *
   * @param maintenanceRecord the {@link MaintenanceRecord} to register;
   *                          ignored if {@code null}
   */
  void registerMaintenance(MaintenanceRecord maintenanceRecord);

  /**
   * Returns an unmodifiable view of the maintenance history.
   *
   * @return list of {@link MaintenanceRecord} entries
   */
  List<MaintenanceRecord> getMaintenanceHistory();
}
