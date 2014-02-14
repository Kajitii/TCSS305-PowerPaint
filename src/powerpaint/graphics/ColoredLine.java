/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.graphics;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;

/**
 * An immutable class that stores the line, color, and stroke produced by the
 * <code>LineTool</code>.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public final class ColoredLine extends AbstractColoredShape {
  
  /**
   * Constructs a new <code>ColoredLine</code> object.
   * 
   * @param the_line the line to be stored.
   * @param the_color the color to be stored.
   * @param the_stroke the stroke/thickness to be stored.
   */
  public ColoredLine(final Line2D.Double the_line,
                          final Color the_color,
                          final Stroke the_stroke) {
    super((Shape) the_line.clone(), the_color, the_stroke);
  }
  
  /**
   * Returns the line.
   * 
   * @return the line.
   */
  public Shape getShape() {
    return (Shape) ((Line2D.Double) my_shape).clone();
  }

}
