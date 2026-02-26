package com.huerta.fleetmanagementsystem.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.huerta.fleetmanagementsystem.model.enums.FuelType;
import com.huerta.fleetmanagementsystem.model.enums.ServiceType;
import com.huerta.fleetmanagementsystem.model.vehicle.Bus;
import com.huerta.fleetmanagementsystem.model.vehicle.Car;
import com.huerta.fleetmanagementsystem.model.vehicle.Motorcycle;
import com.huerta.fleetmanagementsystem.model.vehicle.Truck;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;
import com.huerta.fleetmanagementsystem.service.FleetService;

/**
 * Panel for listing, adding and inspecting vehicles.
 *
 * <p><b>OOP concept – Aggregation:</b> holds a shared reference to
 * {@link FleetService} (not owned; received via constructor).</p>
 *
 * <p><b>OOP concept – Modularity:</b> vehicle-related UI logic is
 * encapsulated in this panel, separate from other concerns.</p>
 */
public class VehiclePanel extends JPanel {

  /** Shared service reference (<b>aggregation</b>). */
  private final FleetService service;

  private final DefaultTableModel tableModel;
  private final JTable table;

  // Common fields
  private final JComboBox<String> typeBox;
  private final JTextField idField;
  private final JTextField plateField;
  private final JTextField makeField;
  private final JTextField modelField;
  private final JTextField yearField;
  private final JTextField mileageField;

  // Car-specific
  private final JTextField seatingCapacityField;
  private final JComboBox<FuelType> fuelTypeBox;

  // Truck-specific
  private final JTextField payloadCapacityField;
  private final JTextField axleCountField;
  private final JTextField grossWeightTruckField;

  // Motorcycle-specific
  private final JTextField displacementField;

  // Bus-specific
  private final JTextField passengerCapacityField;
  private final JComboBox<ServiceType> serviceTypeBox;
  private final JTextField grossWeightBusField;

  // CardLayout for type-specific panels
  private final CardLayout cardLayout;
  private final JPanel specificFieldsPanel;

  public VehiclePanel(FleetService service) {
    this.service = service;

    setLayout(new BorderLayout(10, 10));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // ---- Table ----
    tableModel = new DefaultTableModel(
        new Object[] { "Type", "ID", "Plate", "Make", "Model", "Year", "Mileage", "OpCost" }, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new JTable(tableModel);
    add(new JScrollPane(table), BorderLayout.CENTER);

    // ---- Form ----
    JPanel formWrapper = new JPanel(new BorderLayout(8, 8));
    formWrapper.setBorder(BorderFactory.createTitledBorder("Add Vehicle"));

    // Common fields panel
    JPanel commonPanel = new JPanel(new GridLayout(0, 2, 8, 6));
    typeBox = new JComboBox<>(new String[] { "Car", "Truck", "Motorcycle", "Bus" });
    idField = new JTextField();
    plateField = new JTextField();
    makeField = new JTextField();
    modelField = new JTextField();
    yearField = new JTextField();
    mileageField = new JTextField();

    commonPanel.add(new JLabel("Type:"));
    commonPanel.add(typeBox);
    commonPanel.add(new JLabel("ID:"));
    commonPanel.add(idField);
    commonPanel.add(new JLabel("License Plate:"));
    commonPanel.add(plateField);
    commonPanel.add(new JLabel("Make:"));
    commonPanel.add(makeField);
    commonPanel.add(new JLabel("Model:"));
    commonPanel.add(modelField);
    commonPanel.add(new JLabel("Year:"));
    commonPanel.add(yearField);
    commonPanel.add(new JLabel("Mileage:"));
    commonPanel.add(mileageField);

    // Type-specific panels inside a CardLayout
    cardLayout = new CardLayout();
    specificFieldsPanel = new JPanel(cardLayout);

    // Car card
    seatingCapacityField = new JTextField();
    fuelTypeBox = new JComboBox<>(FuelType.values());
    JPanel carCard = new JPanel(new GridLayout(0, 2, 8, 6));
    carCard.add(new JLabel("Seating Capacity:"));
    carCard.add(seatingCapacityField);
    carCard.add(new JLabel("Fuel Type:"));
    carCard.add(fuelTypeBox);

    // Truck card
    payloadCapacityField = new JTextField();
    axleCountField = new JTextField();
    grossWeightTruckField = new JTextField();
    JPanel truckCard = new JPanel(new GridLayout(0, 2, 8, 6));
    truckCard.add(new JLabel("Payload Capacity (tons):"));
    truckCard.add(payloadCapacityField);
    truckCard.add(new JLabel("Axle Count:"));
    truckCard.add(axleCountField);
    truckCard.add(new JLabel("Gross Weight (tons):"));
    truckCard.add(grossWeightTruckField);

    // Motorcycle card
    displacementField = new JTextField();
    JPanel motoCard = new JPanel(new GridLayout(0, 2, 8, 6));
    motoCard.add(new JLabel("Engine Displacement (cc):"));
    motoCard.add(displacementField);

    // Bus card
    passengerCapacityField = new JTextField();
    serviceTypeBox = new JComboBox<>(ServiceType.values());
    grossWeightBusField = new JTextField();
    JPanel busCard = new JPanel(new GridLayout(0, 2, 8, 6));
    busCard.add(new JLabel("Passenger Capacity:"));
    busCard.add(passengerCapacityField);
    busCard.add(new JLabel("Service Type:"));
    busCard.add(serviceTypeBox);
    busCard.add(new JLabel("Gross Weight (tons):"));
    busCard.add(grossWeightBusField);

    specificFieldsPanel.add(carCard, "Car");
    specificFieldsPanel.add(truckCard, "Truck");
    specificFieldsPanel.add(motoCard, "Motorcycle");
    specificFieldsPanel.add(busCard, "Bus");

    // Show first card by default
    cardLayout.show(specificFieldsPanel, "Car");

    // Switch card when type changes
    typeBox.addActionListener(e -> {
      String selected = (String) typeBox.getSelectedItem();
      cardLayout.show(specificFieldsPanel, selected);
    });

    formWrapper.add(commonPanel, BorderLayout.NORTH);
    formWrapper.add(specificFieldsPanel, BorderLayout.CENTER);

    // Buttons
    JButton addBtn = new JButton("Add Vehicle");
    JButton refreshBtn = new JButton("Refresh");
    JButton reportBtn = new JButton("Generate Fleet Report");
    JButton detailsBtn = new JButton("Vehicle Details");
    JButton commercialBtn = new JButton("Commercial License");

    JPanel actions = new JPanel();
    actions.add(addBtn);
    actions.add(refreshBtn);
    actions.add(reportBtn);
    actions.add(detailsBtn);
    actions.add(commercialBtn);

    JPanel south = new JPanel(new BorderLayout(10, 10));
    south.add(formWrapper, BorderLayout.CENTER);
    south.add(actions, BorderLayout.SOUTH);

    add(south, BorderLayout.SOUTH);

    // ---- Actions ----
    addBtn.addActionListener(e -> addVehicle());
    refreshBtn.addActionListener(e -> refreshTable());
    reportBtn.addActionListener(e -> showFleetReport());
    detailsBtn.addActionListener(e -> showVehicleSpecificDetails());
    commercialBtn.addActionListener(e -> showCommercialLicenseVehicles());

    refreshTable();
  }

  private void addVehicle() {
    try {
      String type = (String) typeBox.getSelectedItem();

      long id = Long.parseLong(idField.getText().trim());
      String plate = plateField.getText().trim();
      String make = makeField.getText().trim();
      String model = modelField.getText().trim();
      int year = Integer.parseInt(yearField.getText().trim());
      double mileage = Double.parseDouble(mileageField.getText().trim());

      Vehicle v;
      switch (type) {
        case "Car":
          int seats = Integer.parseInt(seatingCapacityField.getText().trim());
          FuelType fuel = (FuelType) fuelTypeBox.getSelectedItem();
          v = new Car(id, plate, make, model, year, seats, fuel, mileage);
          break;
        case "Truck":
          int payload = Integer.parseInt(payloadCapacityField.getText().trim());
          int axles = Integer.parseInt(axleCountField.getText().trim());
          double gwTruck = Double.parseDouble(grossWeightTruckField.getText().trim());
          v = new Truck(id, plate, make, model, year, payload, axles, mileage, gwTruck);
          break;
        case "Motorcycle":
          int disp = Integer.parseInt(displacementField.getText().trim());
          v = new Motorcycle(id, plate, make, model, year, disp, mileage);
          break;
        case "Bus":
          int pass = Integer.parseInt(passengerCapacityField.getText().trim());
          ServiceType st = (ServiceType) serviceTypeBox.getSelectedItem();
          double gwBus = Double.parseDouble(grossWeightBusField.getText().trim());
          v = new Bus(id, plate, make, model, year, pass, st, mileage, gwBus);
          break;
        default:
          throw new IllegalArgumentException("Unknown vehicle type");
      }

      service.addVehicle(v);
      JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
      refreshTable();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Invalid Input",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void showFleetReport() {
    java.util.List<String> reports = service.generateFleetReports();
    if (reports.isEmpty()) {
      JOptionPane.showMessageDialog(this, "No vehicles in the fleet.", "Fleet Report",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String fullReport = String.join("\n" + "─".repeat(40) + "\n", reports);
    JTextArea textArea = new JTextArea(fullReport);
    textArea.setEditable(false);
    textArea.setRows(20);
    textArea.setColumns(40);
    JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Fleet Report",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void showVehicleSpecificDetails() {
    java.util.List<String> details = service.getVehicleSpecificDetails();
    if (details.isEmpty()) {
      JOptionPane.showMessageDialog(this, "No vehicles in the fleet.", "Vehicle Details",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    String text = String.join("\n", details);
    JTextArea textArea = new JTextArea(text);
    textArea.setEditable(false);
    textArea.setRows(15);
    textArea.setColumns(50);
    JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Vehicle Specific Details",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void showCommercialLicenseVehicles() {
    java.util.List<Vehicle> vehicles = service.getVehiclesRequiringCommercialLicense();
    if (vehicles.isEmpty()) {
      JOptionPane.showMessageDialog(this, "No vehicles require a commercial license.",
          "Commercial License", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    StringBuilder sb = new StringBuilder();
    sb.append("Vehicles requiring commercial license:\n\n");
    for (Vehicle v : vehicles) {
      sb.append("  \u2022 ").append(v.toString()).append("\n");
    }
    JTextArea textArea = new JTextArea(sb.toString());
    textArea.setEditable(false);
    textArea.setRows(12);
    textArea.setColumns(45);
    JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Commercial License Required",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private void refreshTable() {
    tableModel.setRowCount(0);
    for (Vehicle v : service.vehicles()) {
      tableModel.addRow(new Object[] {
          v.getClass().getSimpleName(),
          v.getId(),
          v.getLicensePlate(),
          v.getMake(),
          v.getModel(),
          v.getYear(),
          v.getMileage(),
          v.calculateOperatingCost()
      });
    }
  }
}
