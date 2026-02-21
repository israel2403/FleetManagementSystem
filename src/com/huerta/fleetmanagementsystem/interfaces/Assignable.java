package com.huerta.fleetmanagementsystem.interfaces;

import com.huerta.fleetmanagementsystem.model.person.Driver;

public interface Assignable {
  void assignDriver(Driver driver);

  void releaseDriver();
}
