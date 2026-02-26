package com.huerta.fleetmanagementsystem.gui;

import java.awt.BorderLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.huerta.fleetmanagementsystem.model.enums.FuelType;
import com.huerta.fleetmanagementsystem.model.enums.MaintenanceType;
import com.huerta.fleetmanagementsystem.model.enums.ServiceType;
import com.huerta.fleetmanagementsystem.model.fleet.Fleet;
import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;
import com.huerta.fleetmanagementsystem.model.person.Driver;
import com.huerta.fleetmanagementsystem.model.vehicle.Bus;
import com.huerta.fleetmanagementsystem.model.vehicle.Car;
import com.huerta.fleetmanagementsystem.model.vehicle.Motorcycle;
import com.huerta.fleetmanagementsystem.model.vehicle.Truck;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;
import com.huerta.fleetmanagementsystem.service.FleetService;

/**
 * Main application window (Swing {@link JFrame}).
 *
 * <p><b>OOP concept – Composition:</b> {@code MainWindow} creates and
 * owns both the {@link FleetService} and every GUI panel; their lifecycle
 * is bound to this window (strong "has-a").</p>
 *
 * <p><b>OOP concept – Modularity:</b> each tab is implemented in its
 * own panel class, keeping responsibilities separated.</p>
 */
public class MainWindow extends JFrame {

  /** Service façade shared by all panels (<b>composition</b> — created here). */
  private final FleetService fleetService;

  /**
   * Constructs the main window, loads sample data and creates all tabs.
   */
  public MainWindow() {
    super("Fleet Management System");

    this.fleetService = new FleetService(new Fleet(null, null));
    loadSampleData();

    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("Vehicles", new VehiclePanel(fleetService));
    tabs.addTab("Drivers", new DriverPanel(fleetService));
    tabs.addTab("Assignments", new AssignmentPanel(fleetService));
    tabs.addTab("Maintenance", new MaintenancePanel(fleetService));

    setLayout(new BorderLayout());
    add(tabs, BorderLayout.CENTER);
  }

  /**
   * Pre-populates the fleet with sample vehicles, drivers and
   * maintenance records for demonstration purposes.
   */
  private void loadSampleData() {
    // -- Vehicles --
    Car car1 = new Car(1, "ABC-1234", "Toyota", "Corolla", 2022, 5, FuelType.GASOLINE, 35000);
    Car car2 = new Car(2, "DEF-5678", "Tesla", "Model 3", 2023, 5, FuelType.ELECTRIC, 12000);
    Car car3 = new Car(3, "GHI-9012", "Honda", "Civic", 2021, 5, FuelType.HYBRID, 48000);

    Truck truck1 = new Truck(4, "TRK-0001", "Volvo", "FH16", 2020, 25000, 3, 120000, 16.0);
    Truck truck2 = new Truck(5, "TRK-0002", "Scania", "R500", 2021, 18000, 2, 85000, 12.0);

    Bus bus1 = new Bus(6, "BUS-0001", "Mercedes-Benz", "Citaro", 2019, 50, ServiceType.CITY, 95000, 18.0);
    Bus bus2 = new Bus(7, "BUS-0002", "Volvo", "9700", 2022, 55, ServiceType.INTERCITY, 42000, 20.0);

    Motorcycle moto1 = new Motorcycle(8, "MOT-0001", "Yamaha", "MT-07", 2023, 689, 8000);
    Motorcycle moto2 = new Motorcycle(9, "MOT-0002", "Honda", "CB500F", 2022, 471, 15000);

    Vehicle[] vehicles = { car1, car2, car3, truck1, truck2, bus1, bus2, moto1, moto2 };
    for (Vehicle v : vehicles) {
      fleetService.addVehicle(v);
    }

    // -- Drivers --
    Driver d1 = new Driver("Carlos Garcia", "LIC-10001", 8);
    Driver d2 = new Driver("Maria Lopez", "LIC-10002", 12);
    Driver d3 = new Driver("Andres Martinez", "LIC-10003", 5);
    Driver d4 = new Driver("Laura Fernandez", "LIC-10004", 15);
    Driver d5 = new Driver("Pedro Sanchez", "LIC-10005", 3);

    Driver[] drivers = { d1, d2, d3, d4, d5 };
    for (Driver d : drivers) {
      fleetService.addDriver(d);
    }

    // -- Maintenance records --
    fleetService.addMaintenance(car1,
        new MaintenanceRecord(LocalDate.of(2025, 6, 15), MaintenanceType.PREVENTIVE, "Oil change and filter replacement", 120.0));
    fleetService.addMaintenance(car1,
        new MaintenanceRecord(LocalDate.of(2025, 11, 3), MaintenanceType.CORRECTIVE, "Brake pad replacement", 280.0));
    fleetService.addMaintenance(car2,
        new MaintenanceRecord(LocalDate.of(2026, 1, 10), MaintenanceType.PREVENTIVE, "Tire rotation and alignment", 95.0));
    fleetService.addMaintenance(truck1,
        new MaintenanceRecord(LocalDate.of(2025, 8, 20), MaintenanceType.CORRECTIVE, "Transmission fluid change", 450.0));
    fleetService.addMaintenance(truck1,
        new MaintenanceRecord(LocalDate.of(2026, 1, 5), MaintenanceType.PREVENTIVE, "Full inspection and oil change", 600.0));
    fleetService.addMaintenance(bus1,
        new MaintenanceRecord(LocalDate.of(2025, 9, 12), MaintenanceType.CORRECTIVE, "Engine coolant system repair", 850.0));
    fleetService.addMaintenance(bus2,
        new MaintenanceRecord(LocalDate.of(2025, 12, 1), MaintenanceType.PREVENTIVE, "Brake system inspection", 320.0));
    fleetService.addMaintenance(moto1,
        new MaintenanceRecord(LocalDate.of(2026, 2, 8), MaintenanceType.PREVENTIVE, "Chain and sprocket replacement", 180.0));
    fleetService.addMaintenance(moto2,
        new MaintenanceRecord(LocalDate.of(2025, 10, 25), MaintenanceType.CORRECTIVE, "Battery replacement", 150.0));
  }
}
