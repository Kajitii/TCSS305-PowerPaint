/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/18/2013 Aaron Chen
 */

package powerpaint.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;

/**
 * The Action that is used to choose a new color.
 * 
 * @author Kajiti
 * @version 0.5.1
 */

public final class ColorChooserAction extends AbstractAction {

  /**
   * The <code>String</code> used for storing the background <code>Color</code> value.
   */
  public static final String BACKGROUND_COLOR = "Background";
  
  /**
   * The <code>String</code> used for storing the foreground <code>Color</code> value.
   */
  public static final String FOREGROUND_COLOR = "Foreground";
  
  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 8999L;
  
  /**
   * The default <code>Color</code>.
   */
  private static final Color DEFAULT_COLOR = Color.BLACK;
  
  /**
   * The dialog window for choosing a new <code>Color</code>.
   */
  private static final JColorChooser COLOR_CHOOSER = new JColorChooser(DEFAULT_COLOR);
  
  /**
   * The color brightness threshold to switch the contrasting color between black and white.
   */
  private static final double COLOR_BRIGHTNESS_THRESHOLD = 1.0;
  
  /**
   * The coefficient for the red component when calculating the perceived brightness of
   * a color.
   */
  private static final double RED_BRIGHTNESS_COEFFICIENT = 1.0 / 156.0;
  
  /**
   * The coefficient for the green component when calculating the perceived brightness of
   * a color.
   */
  private static final double GREEN_BRIGHTNESS_COEFFICIENT = 1.0 / 108.0;
  
  /**
   * The coefficient for the blue component when calculating the perceived brightness of
   * a color.
   */
  private static final double BLUE_BRIGHTNESS_COEFFICIENT = 1.0 / 1280.0;
  
  /**
   * The drawing panel reference.
   */
  private final PowerPaintPanel my_panel;
  
  /**
   * Constructs a new <code>ColorChooserAction</code>.
   * 
   * @param the_panel the drawing panel reference.
   */
  public ColorChooserAction(final PowerPaintPanel the_panel) {
    super();
    my_panel = the_panel;
    putValue(Action.NAME, "Color...");
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
    putValue(ColorChooserAction.BACKGROUND_COLOR, DEFAULT_COLOR);
    putValue(ColorChooserAction.FOREGROUND_COLOR, getContrastingColor(DEFAULT_COLOR));
  }
  
  /**
   * Determines whether black or white would contrast the most from the argument color.
   * 
   * @param the_color the color to be contrasted from.
   * @return the color determined to have the best contrast from <code>the_color</code>.
   */
  private Color getContrastingColor(final Color the_color) {
    Color c = Color.WHITE;
    if (the_color.getRed() * RED_BRIGHTNESS_COEFFICIENT
        + the_color.getGreen() * GREEN_BRIGHTNESS_COEFFICIENT
        + the_color.getBlue() * BLUE_BRIGHTNESS_COEFFICIENT > COLOR_BRIGHTNESS_THRESHOLD) {
      c = Color.BLACK;
    }
    return c;
  }
  
  /**
   * Called upon when a color button has been pressed.  Opens the color chooser dialogue,
   * and sets the color being used.
   * 
   * @param the_event not used.
   */
  @Override
  public void actionPerformed(final ActionEvent the_event) {
    final Color c = JColorChooser.showDialog(my_panel, "Color Chooser",
                                             COLOR_CHOOSER.getColor());
    if (c != null) {
      my_panel.setColor(c);
      putValue(ColorChooserAction.BACKGROUND_COLOR, c);
      putValue(ColorChooserAction.FOREGROUND_COLOR, getContrastingColor(c));
    }
  }
  
}