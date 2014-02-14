/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.tools;

import java.awt.Point;

import powerpaint.graphics.ColoredShape;

/**
 * An interface for PowerPaint tools.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public interface Tool {
  
  /**
   * Feeds an input point so the shape can be generated.
   * 
   * @param the_point the point to be fed.
   */
  void startShape(final Point the_point);
  
  /**
   * Generates the shape from the given input.  Used when the user is finished using the tool
   * once, typically upon mouse release.
   * 
   * @param the_point the point to be used to complete the shape.
   * @return the shape the tool creates.
   */
  ColoredShape generateShape(final Point the_point);
  
}
