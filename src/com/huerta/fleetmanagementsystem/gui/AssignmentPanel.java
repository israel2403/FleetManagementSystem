package com.huerta.fleetmanagementsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.huerta.fleetmanagementsystem.model.person.Driver;
import com.huerta.fleetmanagementsystem.model.vehicle.Vehicle;
import com.huerta.fleetmanagementsystem.service.FleetService;

/**
 * Panel for assigning and releasing drivers to/from vehicles.
 *
 * <p><b>OOP concept – Aggregation:</b> shares the
 * {@link FleetService} reference with the other panels.</p>
 */
public class AssignmentPanel extends JPanel {

  /** Shared service reference (<b>aggregation</b>). */
  private final FleetService service;

  private final DefaultTableModel tableModel;
  private final JComboBox<Vehicle> vehicleBox;
  private final JComboBox<Driver> driverBox;

  public AssignmentPanel(FleetService service) {
    this.service = service;

    setLayout(new BorderLayout(10, 10));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // ---- Table showing current assignments ----
    tableModel = new DefaultTableModel(
        new Object[] { "Vehicle", "Plate", "Assigned Driver", "License #", "Experience (yrs)" }, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    JTable table = new JTable(tableModel);
    add(new JScrollPane(table), BorderLayout.CENTER);

    // ---- Form ----
    JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
    form.setBorder(BorderFactory.createTitledBorder("Assign / Release Driver"));

    vehicleBox = new JComboBox<>();
    driverBox = new JComboBox<>();

    form.add(new JLabel("Vehicle:"));
    form.add(vehicleBox);
    form.add(new JLabel("Driver:"));
    form.add(driverBox);

    // ---- Buttons ----
    JButton assignBtn = new JButton("Assign Driver");
    JButton releaseBtn = new JButton("Release Driver");
    JButton refreshBtn = new JButton("Refresh");

    JPanel actions = new JPanel();
    actions.add(assignBtn);
    actions.add(releaseBtn);
    actions.add(refreshBtn);

    JPanel south = new JPanel(new BorderLayout(10, 10));
    south.add(form, BorderLayout.CENTER);
    south.add(actions, BorderLayout.SOUTH);

    add(south, BorderLayout.SOUTH);

    // ---- Actions ----
    assignBtn.addActionListener(e -> {
      try {
        Vehicle v = (Vehicle) vehicleBox.getSelectedItem();
        Driver d = (Driver) driverBox.getSelectedItem();
        if (v == null) throw new IllegalStateException("No vehicle selected.");
        if (d == null) throw new IllegalStateException("No driver selected.");

        service.assignDriver(v, d);
        JOptionPane.showMessageDialog(this, "Driver assigned successfully.");
        refreshTable();
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
            "Assignment Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    releaseBtn.addActionListener(e -> {
      try {
        Vehicle v = (Vehicle) vehicleBox.getSelectedItem();
        if (v == null) throw new IllegalStateException("No vehicle selected.");

        if (v.getDriver() == null) {
          JOptionPane.showMessageDialog(this, "This vehicle has no assigned driver.",
              "Info", JOptionPane.INFORMATION_MESSAGE);
          return;
        }

        v.releaseDriver();
        JOptionPane.showMessageDialog(this, "Driver released successfully.");
        refreshTable();
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
            "Release Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    refreshBtn.addActionListener(e -> refreshAll());

    refreshAll();
  }

  private void refreshAll() {
    refreshComboBoxes();
    refreshTable();
  }

  private void refreshComboBoxes() {
    vehicleBox.removeAllItems();
    for (Vehicle v : service.vehicles()) {
      vehicleBox.addItem(v);
    }

    driverBox.removeAllItems();
    for (Driver d : service.drivers()) {
      driverBox.addItem(d);
    }
  }

  private void refreshTable() {
    tableModel.setRowCount(0);
    for (Vehicle v : service.vehicles()) {
      Driver d = v.getDriver();
      tableModel.addRow(new Object[] {
          v.getClass().getSimpleName() + " - " + v.getMake() + " " + v.getModel(),
          v.getLicensePlate(),
          d != null ? d.getFullName() : "— Unassigned —",
          d != null ? d.getLicenseNumber() : "",
          d != null ? d.getYearsOfExperience() : ""
      });
    }
  }
}
