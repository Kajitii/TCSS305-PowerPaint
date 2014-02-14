/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.graphics;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

/**
 * An immutable class that stores the rectangle, color, and stroke produced by the
 * <code>RectangleTool</code>.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public final class ColoredRectangle extends AbstractColoredShape {
  
  /**
   * Constructs a new <code>ColoredRectangle</code> object.
   * 
   * @param the_rect the rectangle to be stored.
   * @param the_color the color to be stored.
   * @param the_stroke the stroke/thickness to be stored.
   */
  public ColoredRectangle(final Rectangle2D.Double the_rect,
                          final Color the_color,
                          final Stroke the_stroke) {
    super((Shape) the_rect.clone(), the_color, the_stroke);
  }
  
  /**
   * Returns the rectangle.
   * 
   * @return the rectangle.
   */
  public Shape getShape() {
    return (Shape) ((Rectangle2D.Double) my_shape).clone();
  }

}
