package model.status;

/**
 * The status of an <code>Element</code>
 * @author Krishna Sannasi
 *
 */
public abstract class Status
{
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
}
