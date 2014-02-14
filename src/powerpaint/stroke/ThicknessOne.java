/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.stroke;

import java.awt.event.KeyEvent;

import javax.swing.Action;

import powerpaint.gui.PowerPaintPanel;

/**
 * An action class that sets the thickness of the stroke to 1.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class ThicknessOne extends AbstractThickness {
  
  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 8001L;
  
  /**
   * The thickness that this class sets the stroke to.
   */
  private static final float THICKNESS_VALUE = 1;

  /**
   * Constructs a new <code>ThicknessOne</code> <code>Action</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public ThicknessOne(final PowerPaintPanel the_panel) {
    super(the_panel, THICKNESS_VALUE);
    putValue(Action.SELECTED_KEY, true);
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_1);
  }

}
