package duke;

import static duke.Duke.LINE_DIVIDER;

public class DukeException {

    public static void printEventIsEmpty() {
        System.out.println("Woopsies! The description of an event cannot be empty.");
    }

    public static void printDeadlineIsEmpty() {
        System.out.println("Hey! The deadline is empty!!");
    }

    public static void printCommandIsInvalid() {
        System.out.println("Invalid command! Please try again.");
    }

    public static void listIsEmpty() {
        System.out.println("List is empty!");
        System.out.print(LINE_DIVIDER);
    }
}
