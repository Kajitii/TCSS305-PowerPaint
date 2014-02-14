/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.graphics;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

/**
 * An immutable class that stores the path, color, and stroke produced by the
 * <code>PencilTool</code>.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public final class ColoredPath extends AbstractColoredShape {
  
  /**
   * Constructs a new <code>ColoredPath</code> object.
   * 
   * @param the_path the pencil path to be stored.
   * @param the_color the color to be stored.
   * @param the_stroke the stroke/thickness to be stored.
   */
  public ColoredPath(final GeneralPath the_path,
                          final Color the_color,
                          final Stroke the_stroke) {
    super((Shape) the_path.clone(), the_color, the_stroke);
  }
  
  /**
   * Returns the pencil path.
   * 
   * @return the pencil path.
   */
  public Shape getShape() {
    return (Shape) ((GeneralPath) my_shape).clone();
  }

}
