package Duke.Tasks;

import java.time.LocalDate;

public abstract class Task {

    protected String name;
    protected boolean isDone = false;

    public Task (String nameInit) {
        this.name = nameInit;
    }

    public void setDone() {
        isDone = true;
    }

    public String getName() {
        return name;
    }

    public abstract String toStringSave();

    public abstract LocalDate getDate();

}
