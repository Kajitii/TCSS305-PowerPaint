/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

import powerpaint.gui.PowerPaintGUI;

/**
 * Runs PowerPaint by instantiating and initializing the graphical user interface.
 * 
 * @author Kajiti
 * @version 0.5.1
 */

public final class PowerPaintMain {

  /**
   * Private constructor, to prevent instantiation of this class.
   */
  private PowerPaintMain() {
    throw new IllegalStateException();
  }
  
  /**
   * Main method.
   * Runs the PowerPaint program by creating the GUI and initializing its components.
   * 
   * @param the_args command line arguments, not used.
   */
  public static void main(final String[] the_args) {
    final PowerPaintGUI gui = new PowerPaintGUI();
    gui.init();
  }

}
