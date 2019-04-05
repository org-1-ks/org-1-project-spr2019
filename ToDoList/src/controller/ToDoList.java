package controller;

import java.io.File;

/**
 * 
 * This interface provides a way to communicate between the GUI and the
 * actual model without the two knowing about the other's implementation
 * 
 * This will enable easier testing, as there is less coupling
 * 
 * @author Krishna Sannasi
 *
 */
public interface ToDoList
{
    /**
     * Inserts an element into the ToDo list, 
     * 
     * if the priority of <code>e</code> conflicts with the priority of an element
     * already in the list, then it should bump down the priority of the other item
     * and insert this element into the list
     * 
     * @param e - the element to be inserted
     */
    void insertElement(Element e);
    
    /**
     * Removes the element equal the the given element
     * 
     * @param e
     * @return - true if the element was successfully removed, false otherwise
     */
    boolean removeElement(Element e);
    
    /**
     * Sorts by the given field
     * 
     * @param s - the field to sort by
     */
    void sortBy(Sort s);
    
    /**
     * Moves an element to the given index
     * 
     * @param e - element to move
     * @param index - index to move to
     * @return - true if the element was successfully moved, false otherwise
     */
    boolean moveElementToIndex(Element e, int index);
    
    /**
     * Gets the element in the list that is equal to the given element
     * 
     * @param e
     * @return
     */
    Element getElement(Element e);
    
    /**
     * Write the ToDo list report to the given file
     * 
     * @param file
     */
    void printTo(File file);
    
    /**
     * Saves the ToDo list to a given file
     * 
     * @param file
     */
    void saveTo(File file);
    
    /**
     * Loads the ToDo list from a given file
     * 
     * @param file
     */
    void loadFrom(File file);
    
    /**
     * Gets a list of all of the elements
     *  
     * @return - list of elements
     */
    Element[] getElements();
}
