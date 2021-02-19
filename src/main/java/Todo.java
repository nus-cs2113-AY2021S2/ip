public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() { return "\t[T] " + super.toString();
    }
    public String getDescription() {
        return this.description;
    }
    @Override
    public String stringToSave() {
        return "T ==> " + getStatusIcon() + " ==> " + getDescription();
    }

}
