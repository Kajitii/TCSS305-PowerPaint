/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.tools;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import javax.swing.Action;

import powerpaint.graphics.ColoredLine;
import powerpaint.graphics.ColoredShape;
import powerpaint.gui.PowerPaintPanel;

/**
 * The line tool creates straight lines on the canvas via mouse click and drag.  
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public class LineTool extends AbstractTool {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 9002L;

  /**
   * Constructs a new <code>LineTool</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public LineTool(final PowerPaintPanel the_panel) {
    super(the_panel);
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
  }

  /**
   * Generates and returns a line based on the user's mouse click and release coordinates.
   * 
   * @param the_point the point to be used to complete the shape.
   * @return a straight line.
   */
  @Override
  public ColoredShape generateShape(final Point the_point) {
    return new ColoredLine(new Line2D.Double(my_point_1, the_point),
                                my_panel.getColor(), my_panel.getStroke());
  }
  
}
