public class Todo extends Task{
    /**
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.SYMBOL = "T";
    }
    @Override
    public String toString() { return "\t[" + SYMBOL + "]" + super.toString();
    }
    public String getDescription() {
        return this.description;
    }
    @Override
    public String stringToSave() {
        return SYMBOL + ARROW + getStatusIcon() + ARROW + getDescription();
    }

}
