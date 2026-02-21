package com.huerta.fleetmanagementsystem.model.component;

public class Engine {
  private String engineType;

  private String displacement;

  private String horsepower;

  public Engine() {
  }

  public Engine(String engineType, String displacement, String horsepower) {
    this.engineType = engineType;
    this.displacement = displacement;
    this.horsepower = horsepower;
  }

  public String getEngineType() {
    return engineType;
  }

  public String getDisplacement() {
    return displacement;
  }

  public String getHorsepower() {
    return horsepower;
  }

  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  public void setDisplacement(String displacement) {
    this.displacement = displacement;
  }

  public void setHorsepower(String horsepower) {
    this.horsepower = horsepower;
  }
}
