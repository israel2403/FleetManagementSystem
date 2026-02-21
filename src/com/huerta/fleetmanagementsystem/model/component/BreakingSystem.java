package com.huerta.fleetmanagementsystem.model.component;

import com.huerta.fleetmanagementsystem.model.enums.BreakeType;

public class BreakingSystem {
  private BreakeType brakeType;
  private String status;

  public BreakingSystem() {
  }

  public BreakingSystem(BreakeType brakeType, String status) {
    this.brakeType = brakeType;
    this.status = status;
  }
}
