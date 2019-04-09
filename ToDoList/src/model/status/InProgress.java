package model.status;

import java.time.LocalDate;

public class InProgress extends Status
{
    private LocalDate dateStarted;
    
    public InProgress(LocalDate dateStarted)
    {
        this.dateStarted = dateStarted;
    }

    public LocalDate getDateStarted()
    {
        return dateStarted;
    }

    @Override
    public Type getType()
    {
        return Type.IN_PROGRESS;
    }
}
