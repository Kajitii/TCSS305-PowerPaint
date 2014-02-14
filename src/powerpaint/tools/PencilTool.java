/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;

import javax.swing.Action;

import powerpaint.graphics.ColoredPath;
import powerpaint.graphics.ColoredShape;
import powerpaint.gui.PowerPaintPanel;

/**
 * The pencil tool creates marks defined by the user's mouse movements.  This is the selected
 * tool by default.  
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class PencilTool extends AbstractTool {
  
  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 9001L;
  
  /**
   * The current line being drawn with the pencil.
   */
  private GeneralPath my_gp;

  /**
   * Constructs a new <code>PencilTool</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public PencilTool(final PowerPaintPanel the_panel) {
    super(the_panel);
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
    putValue(Action.SELECTED_KEY, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void startShape(final Point the_point) {
    my_gp = new GeneralPath();
    my_point_1 = (Point) the_point.clone();
    my_gp.moveTo(my_point_1.getX(), my_point_1.getY());
  }

  /**
   * Returns the path created based on the user's mouse click and drag events.
   * 
   * @param the_point the point to be used to add to the path.
   * @return the path created by the user.
   */
  @Override
  public ColoredShape generateShape(final Point the_point) {
    my_gp.lineTo(the_point.getX(), the_point.getY());
    return new ColoredPath((GeneralPath) my_gp.clone(),
                           my_panel.getColor(), my_panel.getStroke());
  }

}
