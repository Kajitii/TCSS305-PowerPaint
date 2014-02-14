/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.Action;

import powerpaint.graphics.ColoredRectangle;
import powerpaint.graphics.ColoredShape;
import powerpaint.gui.PowerPaintPanel;

/**
 * The rectangle tool creates rectangles on the canvas via mouse click and drag.  
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class RectangleTool extends AbstractTool {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 9004L;

  /**
   * Constructs a new <code>RectangleTool</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public RectangleTool(final PowerPaintPanel the_panel) {
    super(the_panel);
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
  }

  /**
   * Generates and returns a rectangle based on the user's mouse click and release
   * coordinates.
   * 
   * @param the_point the point to be used to complete the shape.
   * @return a rectangle.
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
    return new ColoredRectangle(new Rectangle2D.Double(x1, y1, dx, dy),
                                my_panel.getColor(), my_panel.getStroke());
  }
  
}
