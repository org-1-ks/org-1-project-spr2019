package model.status;

import java.time.LocalDate;
import java.util.Objects;

public class NotStarted extends Status
{
    /**
     * 
     */
    private static final long serialVersionUID = 1437639015877684256L;

    public Type getType() {
        return Type.NOT_STARTED;
    }
    
    @Override
    public String toString()
    {
        return "Not Started";
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(toString());
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
       return getClass() == obj.getClass();
    }
    
    public LocalDate getStatusDate()
    {
    	return null ;
    }
}
