package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The main window of the ToDo list
 * 
 * @author Krishna Sannasi
 *
 */
public class Frame extends JFrame
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4743270606172960944L;
    
    private NoSelection noSelection;
    private SelectedItem itemSelected;
    
    private ToolBar toolBar;
    
    public Frame()
    {
        noSelection = new NoSelection();
        itemSelected = null;
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar(this);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
        
        var contents = getContentPane();

        contents.add(toolBar, BorderLayout.NORTH);

        // initial screen should have no selection
        add(noSelection, BorderLayout.CENTER);
        
        // If the frame is closed, the application should exit
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
