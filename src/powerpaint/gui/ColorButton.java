/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/18/2013 Aaron Chen
 */

package powerpaint.gui;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.JButton;

/**
 * A special button class that also changes the background and foreground color, synching
 * with the properties of the Action.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class ColorButton extends JButton {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 6806621139031142831L;
  
  /**
   * Constructs a new <code>ColorButton</code>.
   * 
   * @param the_action the action whose properties should be followed.
   */
  public ColorButton(final Action the_action) {
    super(the_action);
  }
  
  @Override
  protected void configurePropertiesFromAction(final Action the_action) {
    super.configurePropertiesFromAction(the_action);
    setBackground((Color) the_action.getValue(ColorChooserAction.BACKGROUND_COLOR));
    setForeground((Color) the_action.getValue(ColorChooserAction.FOREGROUND_COLOR));
  }
  
  @Override
  protected void actionPropertyChanged(final Action the_action,
                                       final String the_property_name) {
    super.actionPropertyChanged(the_action, the_property_name);
    if (ColorChooserAction.BACKGROUND_COLOR.equals(the_property_name)) {
      setBackground((Color) the_action.getValue(ColorChooserAction.BACKGROUND_COLOR));
    } else if (ColorChooserAction.FOREGROUND_COLOR.equals(the_property_name)) {
      setForeground((Color) the_action.getValue(ColorChooserAction.FOREGROUND_COLOR));
    }
  }

}
