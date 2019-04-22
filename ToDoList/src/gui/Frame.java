package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.Element;
import controller.ToDoList;

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
    private Element selectedItem;
    private final Element defaultElement;
    
    ToDoList list;
    
    public Frame(ToDoList list, Element defaultElement)
    {
        this.list = list;
        this.defaultElement = defaultElement;
        
        noSelection = new NoSelection(this);
        itemSelected = new SelectedItem(this);
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar(this);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
        
        var contents = getContentPane();

        contents.add(toolBar, BorderLayout.NORTH);

        // initial screen should have no selection
        add(noSelection, BorderLayout.CENTER);

//        add(itemSelected, BorderLayout.CENTER);
        
        // If the frame is closed, the application should exit
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    void selectItem() {
        selectedItem = defaultElement;
        
        remove(noSelection);
        add(itemSelected, BorderLayout.CENTER);
        
        itemSelected.use(selectedItem);
        
        revalidate();
    }
    
    void updateList() {
        selectedItem = null;
    }
}
