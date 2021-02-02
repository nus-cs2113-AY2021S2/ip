public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static void printTodoDescription() {
        System.out.println("Gotcha! I've added this task:");
        System.out.print("[T]");
    }

}

