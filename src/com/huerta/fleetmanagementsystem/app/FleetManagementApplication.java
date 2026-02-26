package com.huerta.fleetmanagementsystem.app;

import javax.swing.SwingUtilities;

import com.huerta.fleetmanagementsystem.gui.MainWindow;

/**
 * Application entry point.
 *
 * <p>Launches the Swing GUI on the Event Dispatch Thread.
 * The entire system is started from here, demonstrating how the
 * layered architecture (
 * {@code app → gui → service → model}) is wired together,
 * illustrating <b>modularity</b> across packages.</p>
 */
public class FleetManagementApplication {

  /**
   * Bootstraps the application.
   *
   * @param args command-line arguments (unused)
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainWindow w = new MainWindow();
      w.setVisible(true);
    });
  }
}