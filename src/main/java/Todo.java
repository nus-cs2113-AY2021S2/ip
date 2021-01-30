public class Todo extends Task {

    public Todo(String nameInit) {
        super(nameInit);
    }

    @Override
    public String toString() {
        String outputString = "[T]";
        if (isDone) {
            outputString += "[\u2713]";
        }
        else {
            outputString += "[\u2715]";
        }
        outputString = outputString + " " + name;
        return outputString;
    }

}
