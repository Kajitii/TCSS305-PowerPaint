/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;

import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import powerpaint.graphics.ColoredShape;
import powerpaint.tools.Tool;


/**
 * A JPanel that handles mouse commands to facilitate painting.  
 * 
 * @author Kajiti
 * @version 0.5.1
 */
public final class PowerPaintPanel extends JPanel {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 50001L;
  
  /**
   * The default width of the canvas.
   */
  private static final int DEFAULT_WIDTH = 400;
  
  /**
   * The default height of the canvas.
   */
  private static final int DEFAULT_HEIGHT = 300;
  
  /**
   * The color of the canvas.
   */
  private static final Color DEFAULT_BG_COLOR = Color.WHITE;
  
  /**
   * The color of the grid.  It is partially transparent so users can see underneath.
   */
  private static final Color GRID_COLOR = new Color(0x80, 0x80, 0x80, 0xC0);
  
  /**
   * The grid spacing when shown, in pixels.
   */
  private static final int GRID_SPACING = 10;
  
  /**
   * The default color to paint with.
   */
  private static final Color DEFAULT_COLOR = Color.BLACK;
  
  /**
   * The default stroke to paint with.
   */
  private static final Stroke DEFAULT_STROKE = new BasicStroke();
  
  /**
   * The stack of actions used to create the current image in PowerPaint.
   */
  private static final Stack<ColoredShape> PAINT_ACTIONS = new Stack<ColoredShape>();
  
  /**
   * A secondary stack used for storing actions.
   */
  private static final Stack<ColoredShape> PAINT_ACTION_RESERVES = new Stack<ColoredShape>();
  
  /**
   * A reserve stack used to store actions while drawing.
   */
  private static final Stack<ColoredShape> PAINT_ACTIONS_2 = new Stack<ColoredShape>();
  
  /**
   * The PowerPaintGUI reference.
   */
  private final PowerPaintGUI my_gui;
  
  /**
   * The color currently being used.
   */
  private Color my_color;
  
  /**
   * The stroke currently being used.
   */
  private Stroke my_stroke = new BasicStroke();
  
  /**
   * The tool currently being used.
   */
  private Tool my_tool;
  
  /**
   * The shape currently being drawn.
   */
  private ColoredShape my_preview_shape;
  
  /**
   * Determines if the preview shape should be drawn.
   */
  private boolean my_is_previewing;
  
  /**
   * Determines if the grid should be drawn.
   */
  private boolean my_is_grid_viewable;
  
  
  /**
   * Constructs a new PowerPaintPanel.
   * 
   * @param the_gui the GUI reference.
   */
  public PowerPaintPanel(final PowerPaintGUI the_gui) {
    super();
    my_gui = the_gui;
    my_color = DEFAULT_COLOR;
    setBackground(DEFAULT_BG_COLOR);
    setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    final MouseInputAdapter mia = new MouseActionTracker();
    addMouseListener(mia);
    addMouseMotionListener(mia);
  }
  
  /**
   * Undoes the previous shape drawn, if any.
   */
  public void undo() {
    if (!PAINT_ACTIONS.isEmpty()) {
      PAINT_ACTION_RESERVES.push(PAINT_ACTIONS.pop());
      notifyGUI();
      repaint();
    }
  }
  
  /**
   * Redoes the next shape undone, if any.
   */
  public void redo() {
    if (!PAINT_ACTION_RESERVES.isEmpty()) {
      PAINT_ACTIONS.push(PAINT_ACTION_RESERVES.pop());
      notifyGUI();
      repaint();
    }
  }
  
  /**
   * Clears the panel of all paint.
   */
  public void clear() {
    //Checks for redundancy in clear actions.
    if (PAINT_ACTIONS.peek() != null) {
      PAINT_ACTIONS.push(null);
      notifyGUI();
      repaint();
    }
  }
  
  /**
   * Paints all the shapes previously drawn, the preview of the shape currently being
   * drawn, if any, and the grid.
   * 
   * @param the_g the <code>Graphics</code> used to paint.
   */
  public void paintComponent(final Graphics the_g) {
    final Graphics2D g2d = (Graphics2D) the_g;
    g2d.setColor(DEFAULT_BG_COLOR);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    while (!PAINT_ACTIONS.isEmpty() && PAINT_ACTIONS.peek() != null) {
      PAINT_ACTIONS_2.push(PAINT_ACTIONS.pop());
    }
    while (!PAINT_ACTIONS_2.isEmpty()) {
      final ColoredShape s = PAINT_ACTIONS_2.pop();
      PAINT_ACTIONS.push(s);
      g2d.setColor(s.getColor());
      g2d.setStroke(s.getStroke());
      g2d.draw(s.getShape());
    }
    if (my_is_previewing && my_preview_shape != null) {
      g2d.setColor(my_preview_shape.getColor());
      g2d.setStroke(my_preview_shape.getStroke());
      g2d.draw(my_preview_shape.getShape());
    }
    if (my_is_grid_viewable) {
      g2d.setColor(GRID_COLOR);
      g2d.setStroke(DEFAULT_STROKE);
      for (int i = GRID_SPACING; i < getWidth(); i += GRID_SPACING) {
        g2d.drawLine(i, 0, i, getHeight());
      }
      for (int i = GRID_SPACING; i < getHeight(); i += GRID_SPACING) {
        g2d.drawLine(0, i, getWidth(), i);
      }
    }
  }
  
  /**
   * Returns the color currently being used.
   * 
   * @return the color currently being used.
   */
  public Color getColor() {
    return my_color;
  }
  
  /**
   * Returns the stroke currently being used.
   * 
   * @return the stroke currently being used.
   */
  public Stroke getStroke() {
    return my_stroke;
  }
  
  /**
   * Sets the tool to be used to paint.
   * 
   * @param the_tool the color to be used to paint.
   * @throws IllegalArgumentException if <code>the_tool</code> is <code>null</code>.
   */
  public void setTool(final Tool the_tool) throws IllegalArgumentException {
    if (the_tool == null) {
      throw new IllegalArgumentException("tool cannot be set to null.");
    }
    my_tool = the_tool;
  }
  
  /**
   * Sets the color to be used to paint.
   * 
   * @param the_color the color to be used to paint.
   * @throws IllegalArgumentException if <code>the_color</code> is <code>null</code>.
   */
  public void setColor(final Color the_color) throws IllegalArgumentException {
    if (the_color == null) {
      throw new IllegalArgumentException("color cannot be set to null.");
    }
    my_color = the_color;
  }
  
  /**
   * Sets the stroke to be used to paint.
   * 
   * @param the_stroke the stroke to be used to paint.
   * @throws IllegalArgumentException if <code>the_stroke</code> is <code>null</code>.
   */
  public void setStroke(final Stroke the_stroke) throws IllegalArgumentException {
    if (the_stroke == null) {
      throw new IllegalArgumentException("stroke cannot be set to null.");
    }
    my_stroke = the_stroke;
  }
  
  /**
   * Sets the grid viewability.
   * 
   * @param the_b true if the grid should be visible; false otherwise.
   */
  public void setGridViewable(final boolean the_b) {
    my_is_grid_viewable = the_b;
    repaint();
  }
  
  /**
   * Notifies the parent GUI about the state of its <code>Shape</code>s collection.
   */
  private void notifyGUI() {
    my_gui.update(PowerPaintPanel.this,
                  !PAINT_ACTIONS.isEmpty(),
                  !PAINT_ACTION_RESERVES.isEmpty());
  }
  
  /**
   * The mouse input class for the panel.  Tracks clicks, releases, and drag actions.
   * 
   * @author Kajiti
   * @version 0.5.1
   */
  private class MouseActionTracker extends MouseInputAdapter {
    
    /**
     * {@inheritDoc}
     * <p>
     * Starts drawing according to the tool.
     */
    @Override
    public void mousePressed(final MouseEvent the_event) {
      if (the_event.getButton() == MouseEvent.BUTTON1) {
        my_tool.startShape(the_event.getPoint());
        my_is_previewing = true;
      }
    }
    
    /**
     * {@inheritDoc}
     * <p>
     * Finishes the drawing action according to the tool.  Updates the drawing list of
     * actions accordingly.
     */
    @Override
    public void mouseReleased(final MouseEvent the_event) {
      if (the_event.getButton() == MouseEvent.BUTTON1) {
        my_is_previewing = false;
        final ColoredShape cs = my_tool.generateShape(the_event.getPoint());
        PAINT_ACTIONS.push(cs);
        while (!PAINT_ACTION_RESERVES.isEmpty()) {
          PAINT_ACTION_RESERVES.pop();
        }
        notifyGUI();
        repaint();
      }
    }
    
    /**
     * {@inheritDoc}
     * <p>
     * Previews the shape being drawn and/or draws according to the tool.
     */
    @Override
    public void mouseDragged(final MouseEvent the_event) {
      my_preview_shape = my_tool.generateShape(the_event.getPoint());
      repaint();
    }
  }

}
