public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol = null;
    protected String ddl = null;
    protected String preposition = null;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone_or_not(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public String getPrep() {
        return this.preposition;
    }

    public void setPrep(String prep) {
        this.preposition = prep;
    }


    //...
}
