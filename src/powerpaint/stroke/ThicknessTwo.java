/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.stroke;

import java.awt.event.KeyEvent;

import javax.swing.Action;

import powerpaint.gui.PowerPaintPanel;

/**
 * An action class that sets the thickness of the stroke to 2.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class ThicknessTwo extends AbstractThickness {
  
  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 8002L;
  
  /**
   * The thickness that this class sets the stroke to.
   */
  private static final float THICKNESS_VALUE = 2.0f;

  /**
   * Constructs a new <code>ThicknessTwo</code> <code>Action</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public ThicknessTwo(final PowerPaintPanel the_panel) {
    super(the_panel, THICKNESS_VALUE);
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_2);
  }

}
