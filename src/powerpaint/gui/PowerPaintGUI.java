/*
 * TCSS305 Assignment 5: PowerPaint
 * 5/7/2013 Aaron Chen
 */

package powerpaint.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import powerpaint.stroke.*;
import powerpaint.tools.*;

/**
 * The graphical user interface for the PowerPaint program.
 * 
 * @author Kajiti
 * @version 0.5.2
 */

public final class PowerPaintGUI extends JFrame {

  //Constants and fields
  
  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 9372008L;
  
  /**
   * The title of the program.
   */
  private static final String PROGRAM_TITLE = "TCSS 305 PowerPaint, Spring 2013";
  
  /**
   * The label of the tools menu.
   */
  private static final String TOOLS_LABEL = "Tools";
  
  /**
   * The "Undo" JMenuItem.
   */
  private static final JMenuItem UNDO_JMENUITEM = new JMenuItem("Undo");
  
  /**
   * The "Redo" JMenuItem.
   */
  private static final JMenuItem REDO_JMENUITEM = new JMenuItem("Redo");
  
  /**
   * The panel which the user can paint on.
   */
  private final PowerPaintPanel my_power_paint_panel = new PowerPaintPanel(this);
  
  /**
   * The <code>Action</code> to be used when the user wants to choose a color.
   */
  private final Action my_color_chooser_action = new ColorChooserAction(my_power_paint_panel);
  
  /**
   * The list of tools to be used in PowerPaint.
   */
  private final Tool[] my_tools = {
    new PencilTool(my_power_paint_panel),
    new LineTool(my_power_paint_panel),
    new RectangleTool(my_power_paint_panel),
    new EllipseTool(my_power_paint_panel)
  };
  
  /**
   * The list of thicknesses to be used in PowerPaint.
   */
  private final Action[] THICKNESSES = {
    new ThicknessOne(my_power_paint_panel),
    new ThicknessTwo(my_power_paint_panel),
    new ThicknessFour(my_power_paint_panel)
  };
  
  
  //Constructor and methods
  
  /**
   * Constructs a new PowerPaintGUI.
   */
  public PowerPaintGUI() {
    super(PROGRAM_TITLE);
  }
  
  /**
   * Initializes all the GUI components for PowerPaintGUI.
   */
  public void init() {
    setJMenuBar(createJMenuBar());
    add(my_power_paint_panel, BorderLayout.CENTER);
    my_power_paint_panel.setTool(my_tools[0]);
    
    final JToolBar tool_bar = createJToolBar();
    add(tool_bar, BorderLayout.PAGE_END);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationByPlatform(true);
    setVisible(true);
  }
  
  /**
   * Creates the <code>JMenuBar</code> for the PowerPaint GUI.
   * 
   * @return the menu bar for the PowerPaint program.
   */
  private JMenuBar createJMenuBar() {
    final JMenuBar menu_bar = new JMenuBar();
    menu_bar.add(createFileJMenu());
    menu_bar.add(createOptionsJMenu());
    menu_bar.add(createToolsJMenu());
    menu_bar.add(createHelpJMenu());
    return menu_bar;
  }
  
  /**
   * Creates the file <code>JMenu</code>. 
   * 
   * @return the file <code>JMenu</code>. 
   */
  private JMenu createFileJMenu() {
    final JMenu file_menu = new JMenu("File");
    file_menu.setMnemonic(KeyEvent.VK_F);
    
    //final JMenuItem undo_item = new JMenuItem("Undo");
    UNDO_JMENUITEM.setMnemonic(KeyEvent.VK_U);
    UNDO_JMENUITEM.addActionListener(new ActionListener() {
      /** Called upon when the "Undo" menu item is selected. */
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_power_paint_panel.undo();
      }
    });
    UNDO_JMENUITEM.setEnabled(false);
    
    //final JMenuItem redo_item = new JMenuItem("Redo");
    REDO_JMENUITEM.setMnemonic(KeyEvent.VK_R);
    REDO_JMENUITEM.addActionListener(new ActionListener() {
      /** Called upon when the "Redo" menu item is selected. */
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_power_paint_panel.redo();
      }
    });
    REDO_JMENUITEM.setEnabled(false);
    
    final JMenuItem clear_item = new JMenuItem("Clear");
    clear_item.setMnemonic(KeyEvent.VK_C);
    clear_item.addActionListener(new ActionListener() {
      /** Clears the panel of all paint. */
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_power_paint_panel.clear();
      }
    });
    
    final JMenuItem exit_item = new JMenuItem("Exit");
    exit_item.setMnemonic(KeyEvent.VK_X);
    exit_item.addActionListener(new ActionListener() {
      /** Closes PowerPaint. */
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        dispose();
      }
    });
    
    file_menu.add(UNDO_JMENUITEM);
    file_menu.add(REDO_JMENUITEM);
    file_menu.add(clear_item);
    file_menu.addSeparator();
    file_menu.add(exit_item);
    
    return file_menu;
  }
  
  /**
   * Creates the options <code>JMenu</code>.
   * 
   * @return the options <code>JMenu</code>.
   */
  private JMenu createOptionsJMenu() {
    final JMenu options_menu = new JMenu("Options");
    options_menu.setMnemonic(KeyEvent.VK_O);
    
    final JCheckBoxMenuItem grid_item = new JCheckBoxMenuItem("Grid");
    grid_item.setMnemonic(KeyEvent.VK_G);
    grid_item.addActionListener(new ActionListener() {
      /** Sets the drawing grid's visibility. */
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        my_power_paint_panel.setGridViewable(grid_item.isSelected());
      }
    });
    
    final JMenu thickness_item = new JMenu("Thickness");
    thickness_item.setMnemonic(KeyEvent.VK_T);
    
    final ButtonGroup group = new ButtonGroup();
    for (final Action a : THICKNESSES) {
      final JRadioButtonMenuItem thickness_button = new JRadioButtonMenuItem(a);
      thickness_item.add(thickness_button);
      group.add(thickness_button);
    }
    
    options_menu.add(grid_item);
    options_menu.add(thickness_item);
    
    return options_menu;
  }
  
  /**
   * Creates the tools <code>JMenu</code>.
   * 
   * @return the tools <code>JMenu</code>.
   */
  private JMenu createToolsJMenu() {
    final JMenu tools_menu = new JMenu(TOOLS_LABEL);
    tools_menu.setMnemonic(KeyEvent.VK_T);
    
    final JMenuItem color_item = new ColorMenuItem(my_color_chooser_action);
    tools_menu.add(color_item);
    tools_menu.addSeparator();
    
    final ButtonGroup group = new ButtonGroup();
    for (final Tool tool : my_tools) {
      final JRadioButtonMenuItem tool_item = new JRadioButtonMenuItem((Action) tool);
      tools_menu.add(tool_item);
      group.add(tool_item);
    }
    group.getElements().nextElement().doClick();
    
    return tools_menu;
  }
  
  /**
   * Creates the help <code>JMenu</code>.
   * 
   * @return the help <code>JMenu</code>.
   */
  private JMenu createHelpJMenu() {
    final JMenu help_menu = new JMenu("Help");
    help_menu.setMnemonic(KeyEvent.VK_H);
    
    final JMenuItem about_item = new JMenuItem("About...");
    about_item.setMnemonic(KeyEvent.VK_A);
    about_item.addActionListener(new ActionListener() {
      /** Shows the information dialog for PowerPaint. */
      @Override
      public void actionPerformed(final ActionEvent the_event) {
        JOptionPane.showMessageDialog(PowerPaintGUI.this, "TCSS 305, Spring 2013\n"
                                      + "PowerPaint version 0.5.1", "About",
                                      JOptionPane.INFORMATION_MESSAGE);
      }
    });
    
    help_menu.add(about_item);
    
    return help_menu;
  }
  
  /**
   * Creates the <code>JToolBar</code> that holds the color picker and tools.
   * 
   * @return the <code>JToolBar</code> for PowerPaint.
   */
  private JToolBar createJToolBar() {
    final JToolBar tool_bar = new JToolBar("Toolbar");
    
    final JButton color_button = new ColorButton(my_color_chooser_action);
    tool_bar.add(color_button);
    
    final ButtonGroup group = new ButtonGroup();
    for (final Tool tool : my_tools) {
      final JToggleButton button = new JToggleButton((Action) tool);
      group.add(button);
      tool_bar.add(button);
    }
    group.getElements().nextElement().doClick();
    return tool_bar;
  }
  
  /**
   * Called by the object responsible for managing the number of shapes currently drawn.
   * Depending on the argument passed, functionality for some of the GUI's actions may be
   * enabled or disabled.
   * 
   * @param the_obj the object calling this method.
   * @param the_b1 the first value to be examined.
   * @param the_b2 the second value to be examined.
   * @throws IllegalArgumentException if an unexpected entity is calling this method.
   */
  public void update(final Object the_obj, final boolean the_b1, final boolean the_b2)
    throws IllegalArgumentException {
    if (!the_obj.equals(my_power_paint_panel)) {
      throw new IllegalArgumentException("only the PowerPaintPanel should call"
                                         + "PowerPaintGUI's update method.");
    }
    UNDO_JMENUITEM.setEnabled(the_b1);
    REDO_JMENUITEM.setEnabled(the_b2);
  }
  
}