/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/19/2013 Aaron Chen
 */

package powerpaint.graphics;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * An interface for an immutable class that stores the shape, color, and stroke produced
 * by a PowerPaint tool.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public interface ColoredShape {
  
  /**
   * Returns the shape.
   * @return the shape.
   */
  Shape getShape();
  
  /**
   * Returns the color of the shape.
   * @return the color.
   */
  Color getColor();
  
  /**
   * Returns the stroke/thickness of the shape.
   * @return the stroke.
   */
  Stroke getStroke();
}
