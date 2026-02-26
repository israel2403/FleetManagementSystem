package com.huerta.fleetmanagementsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.huerta.fleetmanagementsystem.model.enums.MaintenanceType;
import com.huerta.fleetmanagementsystem.model.maintenance.MaintenanceRecord;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;
import com.huerta.fleetmanagementsystem.service.FleetService;

/**
 * Panel for registering maintenance records against vehicles.
 *
 * <p><b>OOP concept – Aggregation:</b> shares the
 * {@link FleetService} reference with the other panels.</p>
 */
public class MaintenancePanel extends JPanel {

  /** Shared service reference (<b>aggregation</b>). */
  private final FleetService service;

  public MaintenancePanel(FleetService service) {
    this.service = service;

    setLayout(new BorderLayout(10, 10));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
    form.setBorder(BorderFactory.createTitledBorder("Register Maintenance"));

    JComboBox<Vehicle> vehicleBox = new JComboBox<>();
    refreshVehicleBox(vehicleBox);

    JTextField dateField = new JTextField("2026-02-23"); // ISO date
    JComboBox<MaintenanceType> typeBox = new JComboBox<>(MaintenanceType.values());
    JTextField descField = new JTextField();
    JTextField costField = new JTextField();

    form.add(new JLabel("Vehicle:"));
    form.add(vehicleBox);
    form.add(new JLabel("Date (YYYY-MM-DD):"));
    form.add(dateField);
    form.add(new JLabel("Type:"));
    form.add(typeBox);
    form.add(new JLabel("Description:"));
    form.add(descField);
    form.add(new JLabel("Cost:"));
    form.add(costField);

    JButton addBtn = new JButton("Register Maintenance");
    JButton refreshBtn = new JButton("Refresh Vehicles");

    JPanel actions = new JPanel();
    actions.add(addBtn);
    actions.add(refreshBtn);

    add(form, BorderLayout.CENTER);
    add(actions, BorderLayout.SOUTH);

    addBtn.addActionListener(e -> {
      try {
        Vehicle v = (Vehicle) vehicleBox.getSelectedItem();
        if (v == null)
          throw new IllegalStateException("No vehicle available. Add a vehicle first.");

        LocalDate date = LocalDate.parse(dateField.getText().trim());
        MaintenanceType type = (MaintenanceType) typeBox.getSelectedItem();
        String desc = descField.getText().trim();
        double cost = Double.parseDouble(costField.getText().trim());

        service.addMaintenance(v, new MaintenanceRecord(date, type, desc, cost));
        JOptionPane.showMessageDialog(this, "Maintenance registered successfully.");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
      }
    });

    refreshBtn.addActionListener(e -> refreshVehicleBox(vehicleBox));
  }

  private void refreshVehicleBox(JComboBox<Vehicle> vehicleBox) {
    vehicleBox.removeAllItems();
    for (Vehicle v : service.vehicles()) {
      vehicleBox.addItem(v);
    }
  }
}