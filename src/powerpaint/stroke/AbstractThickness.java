/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.stroke;

import java.awt.BasicStroke;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import powerpaint.gui.PowerPaintPanel;

/**
 * An abstract class that extends <code>AbstractAction</code> for convenient usage in
 * button groups.  Classes that extend this abstract class should be responsible for
 * setting the stroke being used in PowerPaint.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public abstract class AbstractThickness extends AbstractAction {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 7999L;
  
  /**
   * The drawing panel reference.
   */
  protected final PowerPaintPanel my_panel;

  /**
   * The thickness that this class sets the stroke to.
   */
  private final float my_thickness_value;
  
  /**
   * Constructs a new <code>AbstractThickness</code> object.
   * 
   * @param the_panel the drawing panel reference.
   * @param the_n the value that the stroke's thickness will be set to.
   */
  protected AbstractThickness(final PowerPaintPanel the_panel, final float the_n) {
    super();
    my_panel = the_panel;
    my_thickness_value = the_n;
    putValue(Action.NAME, Integer.toString(Math.round(the_n)));
    putValue(Action.SELECTED_KEY, false);
  }

  /**
   * Called when the button this action is assigned to is clicked on.  Sets the stroke
   * being used to have a thickness of <code>my_thickness_value</code>.
   * 
   * @param the_event not used.
   */
  @Override
  public void actionPerformed(final ActionEvent the_event) {
    my_panel.setStroke(new BasicStroke(my_thickness_value));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(getClass().getName());
    sb.append("[Stroke Thickness:");
    sb.append(my_thickness_value);
    sb.append(",");
    sb.append(super.toString());
    return sb.toString();
  }
  
}
