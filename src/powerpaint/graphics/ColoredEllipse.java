/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.graphics;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

/**
 * An immutable class that stores the ellipse, color, and stroke produced by the
 * <code>EllipseTool</code>.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public final class ColoredEllipse extends AbstractColoredShape {
  
  /**
   * Constructs a new <code>ColoredEllipse</code> object.
   * 
   * @param the_ellipse the ellipse to be stored.
   * @param the_color the color to be stored.
   * @param the_stroke the stroke/thickness to be stored.
   */
  public ColoredEllipse(final Ellipse2D.Double the_ellipse,
                          final Color the_color,
                          final Stroke the_stroke) {
    super((Shape) the_ellipse.clone(), the_color, the_stroke);
  }
  
  /**
   * Returns the ellipse.
   * 
   * @return the ellipse.
   */
  public Shape getShape() {
    return (Shape) ((Ellipse2D.Double) my_shape).clone();
  }

}
