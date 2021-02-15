package duke;

public class DukeException {

    public static boolean checkTodo(String command) {
        boolean checkIfEmpty = false;
        if (command.length() == 4) {
            System.out.println("Woopies! The description of a todo cannot be empty.");
            checkIfEmpty = true;
        }
        return checkIfEmpty;
    }

    public static void eventIsEmpty() {
        System.out.println("Woopsies! The description of an event cannot be empty.");
    }

    public static void deadlineIsEmpty() {
        System.out.println("Hey! The deadline is empty!!");
    }

    public static void commandIsInvalid() {
        System.out.println("Invalid command! Please try again.");
    }

    public static void listIsEmpty() {
        System.out.println("Please enter a task!");
    }
}
