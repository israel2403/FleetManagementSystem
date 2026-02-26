package com.huerta.fleetmanagementsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.huerta.fleetmanagementsystem.model.person.Driver;
import com.huerta.fleetmanagementsystem.service.FleetService;

/**
 * Panel for listing and adding drivers.
 *
 * <p><b>OOP concept – Aggregation:</b> holds a shared reference to
 * {@link FleetService} (not owned).</p>
 */
public class DriverPanel extends JPanel {

  /** Shared service reference (<b>aggregation</b>). */
  private final FleetService service;

  private final DefaultTableModel tableModel;
  private final JTable table;

  public DriverPanel(FleetService service) {
    this.service = service;

    setLayout(new BorderLayout(10, 10));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    tableModel = new DefaultTableModel(new Object[] { "Full Name", "License Number", "Years of Experience" }, 0) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new JTable(tableModel);
    add(new JScrollPane(table), BorderLayout.CENTER);

    JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
    form.setBorder(BorderFactory.createTitledBorder("Add Driver"));

    JTextField nameField = new JTextField();
    JTextField licenseField = new JTextField();
    JTextField yearsField = new JTextField();

    form.add(new JLabel("Full Name:"));
    form.add(nameField);
    form.add(new JLabel("License Number:"));
    form.add(licenseField);
    form.add(new JLabel("Years of Experience:"));
    form.add(yearsField);

    JButton addBtn = new JButton("Add Driver");
    JButton refreshBtn = new JButton("Refresh");

    JPanel actions = new JPanel();
    actions.add(addBtn);
    actions.add(refreshBtn);

    JPanel south = new JPanel(new BorderLayout(10, 10));
    south.add(form, BorderLayout.CENTER);
    south.add(actions, BorderLayout.SOUTH);
    add(south, BorderLayout.SOUTH);

    addBtn.addActionListener(e -> {
      try {
        String name = nameField.getText().trim();
        String license = licenseField.getText().trim();
        int years = Integer.parseInt(yearsField.getText().trim());

        service.addDriver(new Driver(name, license, years));
        JOptionPane.showMessageDialog(this, "Driver added successfully.");
        refreshTable();
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
      }
    });

    refreshBtn.addActionListener(e -> refreshTable());

    refreshTable();
  }

  private void refreshTable() {
    tableModel.setRowCount(0);
    for (Driver d : service.drivers()) {
      tableModel.addRow(new Object[] { d.getFullName(), d.getLicenseNumber(), d.getYearsOfExperience() });
    }
  }
}