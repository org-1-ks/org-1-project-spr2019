package model.status;

import java.io.Serializable;
import java.time.LocalDate ;

/**
 * The status of an <code>Element</code>
 * @author Krishna Sannasi
 *
 */
public abstract class Status implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 347354487474721357L;

    public static enum Type {
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }
    
    abstract public Type getType();
    
    @Override
    abstract public boolean equals(Object obj);
    
    @Override
    abstract public int hashCode();
    
    abstract public LocalDate getStatusDate() ;
}
