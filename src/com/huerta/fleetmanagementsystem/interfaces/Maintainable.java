package com.huerta.fleetmanagementsystem.interfaces;

import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;

public interface Maintainable {
  void registerMaintenance(MaintenanceRecord maintenanceRecord);

}
