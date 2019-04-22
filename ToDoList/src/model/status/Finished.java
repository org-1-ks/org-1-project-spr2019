package model.status;

import java.time.LocalDate;
import java.util.Objects;

public class Finished extends Status
{
    private LocalDate dateFinished;
    
    public Finished(LocalDate dateFinished)
    {
        this.dateFinished = dateFinished;
    }

    @Override
    public Type getType()
    {
        return Type.FINISHED;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(dateFinished);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Finished other = (Finished) obj;
        return Objects.equals(dateFinished, other.dateFinished);
    }
    
    public LocalDate getStatusDate()
    {
    	return dateFinished ;
    }
    
    public String toString()
    {
    	return String.format("Finished (finished on %s)", dateFinished);
    }
}
