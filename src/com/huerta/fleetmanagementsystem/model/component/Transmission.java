package com.huerta.fleetmanagementsystem.model.component;

import com.huerta.fleetmanagementsystem.model.enums.TransmissionType;

public class Transmission {
  private int numberOfGears;

  TransmissionType transmissionType;

  public Transmission() {
  }

  public Transmission(int numberOfGears, TransmissionType transmissionType) {
    this.numberOfGears = numberOfGears;
    this.transmissionType = transmissionType;
  }

  public int getNumberOfGears() {
    return numberOfGears;
  }

  public void setNumberOfGears(int numberOfGears) {
    this.numberOfGears = numberOfGears;
  }

  public TransmissionType getTransmissionType() {
    return transmissionType;
  }

  public void setTransmissionType(TransmissionType transmissionType) {
    this.transmissionType = transmissionType;
  }
}
