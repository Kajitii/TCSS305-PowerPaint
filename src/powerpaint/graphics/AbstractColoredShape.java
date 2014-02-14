/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/10/2013 Aaron Chen
 */

package powerpaint.graphics;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * An abstract class that stores the shape and color produced by a PowerPaint tool.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public abstract class AbstractColoredShape implements ColoredShape {

  /**
   * The shape being stored.
   */
  protected final Shape my_shape;
  
  /**
   * The color of the shape.
   */
  private final Color my_color;
  
  /**
   * The stroke/thickness of the shape.
   */
  private final Stroke my_stroke;
  
  /**
   * Constructs a new <code>ColoredShape</code> object.
   * 
   * @param the_shape the shape to be stored.
   * @param the_color the color to be stored.
   * @param the_stroke the stroke/thickness to be stored.
   */
  public AbstractColoredShape(final Shape the_shape,
                              final Color the_color,
                              final Stroke the_stroke) {
    
    my_shape = the_shape;
    my_color = the_color;
    my_stroke = the_stroke;
  }
  
  /**
   * Returns the color of the shape.
   * @return the color.
   */
  public Color getColor() {
    return my_color;
  }
  
  /**
   * Returns the stroke/thickness of the shape.
   * @return the stroke.
   */
  public Stroke getStroke() {
    return my_stroke;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append(getClass().getName());
    sb.append("[");
    sb.append(my_shape);
    sb.append(",");
    sb.append(my_color);
    sb.append(",");
    sb.append(my_stroke);
    sb.append("]");
    return sb.toString();
  }
  
}
