package model.status;

import java.time.LocalDate;

public class Finished extends Status
{
    private LocalDate dateFinished;
    
    public Finished(LocalDate dateFinished)
    {
        this.dateFinished = dateFinished;
    }

    public LocalDate getDateFinished()
    {
        return dateFinished;
    }

    @Override
    public Type getType()
    {
        return Type.FINISHED;
    }
}
