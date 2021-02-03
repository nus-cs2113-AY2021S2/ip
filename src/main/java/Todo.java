public class Todo extends Task {

    public Todo(String description) {
        super(description);
        System.out.print("Got it. I've added this task:\n" + this + '\n');
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
