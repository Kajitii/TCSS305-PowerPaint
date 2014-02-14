/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.tools;

import java.awt.Point;
import java.awt.event.ActionEvent;

import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import powerpaint.gui.PowerPaintPanel;

/**
 * The abstract superclass for all PowerPaint tools.
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public abstract class AbstractTool extends AbstractAction implements Tool {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 9000L;

  /**
   * The length of the <code>String</code> "Tool".
   */
  private static final String TOOL_SUFFIX = "Tool";
  
  /**
   * The first point to be used when generating the shape.
   */
  protected Point my_point_1;
  
  /**
   * The drawing panel reference.
   */
  protected final PowerPaintPanel my_panel;
  
  /**
   * Constructs the tool and sets <code>my_description</code> to be a variant of the class
   * name.  Specifically, any package hierarchy in the name is removed, and the suffix "Tool",
   * if it exists, is removed.
   * 
   * @param the_panel the drawing panel reference.
   */
  protected AbstractTool(final PowerPaintPanel the_panel) {
    super();
    my_panel = the_panel;
    String desc = getClass().getName();
    final int dot = desc.lastIndexOf('.');
    if (desc.endsWith(TOOL_SUFFIX)) {
      desc = desc.substring(dot + 1, desc.length() - TOOL_SUFFIX.length());
    } else {
      desc = desc.substring(dot + 1, desc.length());
    }
    putValue(Action.NAME, desc);
    putValue(Action.SMALL_ICON,
             new ImageIcon(getClass().getResource("/images/" + desc.toLowerCase(Locale.ENGLISH)
                                                  + "_bw.gif")));
    putValue(Action.SELECTED_KEY, false);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void startShape(final Point the_point) {
    my_point_1 = (Point) the_point.clone();
  }

  /**
   * Called when the button this class is associated with is clicked.  Sets this tool as
   * the active tool.
   * 
   * @param the_event not used.
   */
  @Override
  public void actionPerformed(final ActionEvent the_event) {
    my_panel.setTool(this);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return (String) getValue(Action.NAME);
  }
  
}
