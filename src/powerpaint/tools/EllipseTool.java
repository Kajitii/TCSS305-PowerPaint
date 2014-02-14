/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.Action;

import powerpaint.graphics.ColoredEllipse;
import powerpaint.graphics.ColoredShape;
import powerpaint.gui.PowerPaintPanel;

/**
 * The line tool creates lines on the canvas via mouse click and drag.  
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class EllipseTool extends AbstractTool {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 9003L;

  /**
   * Constructs a new <code>EllipseTool</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public EllipseTool(final PowerPaintPanel the_panel) {
    super(the_panel);
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
  }

  /**
   * Generates and returns an ellipse based on the user's mouse click and release
   * coordinates.
   * 
   * @param the_point the point to be used to complete the shape.
   * @return an ellipse.
   */
  @Override
  public ColoredShape generateShape(final Point the_point) {
    double x1 = my_point_1.getX();
    double y1 = my_point_1.getY();
    if (the_point.getX() < x1) {
      x1 = the_point.getX();
    }
    if (the_point.getY() < y1) {
      y1 = the_point.getY();
    }
    final double dx = Math.abs(my_point_1.getX() - the_point.getX());
    final double dy = Math.abs(my_point_1.getY() - the_point.getY());
    return new ColoredEllipse(new Ellipse2D.Double(x1, y1, dx, dy),
                              my_panel.getColor(), my_panel.getStroke());
  }

}
