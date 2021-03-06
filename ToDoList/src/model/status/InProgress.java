package model.status;

import java.time.LocalDate;
import java.util.Objects;

public class InProgress extends Status {
    private static final long serialVersionUID = -8883957089048504772L;
    
    private LocalDate dateStarted;
    
    public InProgress(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }
    
    @Override
    public Type getType() {
        return Type.IN_PROGRESS;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(dateStarted);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InProgress other = (InProgress) obj;
        return Objects.equals(dateStarted, other.dateStarted);
    }
    
    public LocalDate getStatusDate() {
        return dateStarted;
    }
    
    public String toString() {
        return String.format("In Progress (started on %s)", dateStarted);
    }
}
