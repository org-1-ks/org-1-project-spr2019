package model.status;

import java.util.Objects;

public class NotStarted extends Status
{
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
}
