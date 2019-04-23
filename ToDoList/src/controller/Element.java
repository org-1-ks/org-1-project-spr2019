package controller;

import java.io.Serializable;
import java.time.LocalDate;

import model.status.Status;

/**
 * Elements represent an immutable item in the ToDo list
 * 
 * @author Krishna Sannasi
 *
 */
public interface Element extends Serializable {
    /**
     * @return - priority of this element
     */
    int getPriority();
    
    /**
     * @return - due date of this element
     */
    LocalDate getDueDate();
    
    /**
     * @return - status of this element
     */
    Status getStatus();
    
    /**
     * @return - name of this element
     */
    String getName();
    
    /**
     * @return - description of this element
     */
    String getDescription();
    
    /**
     * @return - an element with the given priority and all other properties are
     *         the same as the current element
     */
    Element withPriority(int priority);
    
    /**
     * @return - an element with the given due date and all other properties are
     *         the same as the current element
     */
    Element withDueDate(LocalDate date);
    
    /**
     * @return - an element with the given status and all other properties are
     *         the same as the current element
     */
    Element withStatus(Status status);
    
    /**
     * @return - an element with the given name and all other properties are the
     *         same as the current element
     */
    Element withName(String name);
    
    /**
     * @return - an element with the given description and all other properties
     *         are the same as the current element
     */
    Element withDescription(String description);
    
    /**
     * @return - String of the element to be printed on a readable page
     */
    String toString();
}
