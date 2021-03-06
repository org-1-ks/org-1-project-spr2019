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
public class Frame extends JFrame {
    private static final long serialVersionUID = 4743270606172960944L;
    
    private NoSelection noSelection;
    private SelectedItem itemSelected;
    
    private ToolBar toolBar;
    
    Element selectedItem, defaultItem;
    ToDoList list;
    
    /**
     * Sets the window for all operations on the list to take place.
     * 
     * @param list
     * @param defaultElement
     */
    public Frame(ToDoList list, Element defaultElement) {
        this.list = list;
        this.selectedItem = defaultElement;
        this.defaultItem = defaultElement;
        
        noSelection = new NoSelection(this);
        itemSelected = new SelectedItem(this);
        itemSelected.setVisible(false);
        
        setLayout(new BorderLayout());
        
        toolBar = new ToolBar(this);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(screenSize.width * 2 / 3, screenSize.height * 2 / 3);
        
        add(toolBar, BorderLayout.NORTH);
        
        // initial screen should have no selection
        add(noSelection, BorderLayout.CENTER);
        
        // If the frame is closed, the application should exit
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Allows to open a new window when an item is selected.
     */
    void selectItem() {
        remove(noSelection);
        
        add(itemSelected, BorderLayout.CENTER);
        noSelection.setVisible(false);
        itemSelected.setVisible(true);
        itemSelected.use(selectedItem);
        toolBar.insertOrRemove(false);
        
        revalidate();
    }
    
    /**
     * Changes the appearance of the window when an item is not selected.
     */
    void unselectItem() {
        remove(itemSelected);
        add(noSelection, BorderLayout.CENTER);
        itemSelected.setVisible(false);
        noSelection.setVisible(true);
        toolBar.insertOrRemove(true);
        revalidate();
    }
    
    /**
     * Updated the list when an item is editted
     */
    void updateList() {
        noSelection.updateList(this.list);
    }
    
    /**
     * This function either inserts or removes an element based on what screen
     * we are currently on
     */
    void insertOrRemove() {
        if (itemSelected.isVisible()) {
            list.removeElement(selectedItem);
            updateList();
            unselectItem();
        } else {
            selectedItem = defaultItem;
            selectItem();
        }
    }
}
