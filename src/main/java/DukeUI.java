public class DukeUI {

    public static void printWelcomeMessage(){
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n\n");
    }

    public static void printExitMessage(){
        System.out.printf("Bye. Hope to see you again soon!\n");
    }

    public static void printMenu() {
        System.out.printf("--------User Menu--------\n" +
                "list: list current tasks and completion status\n" +
                "done: Mark a task as completed\n" +
                "bye: Exit\n" +
                "any other words: The word will be recorded as a task\n");
    }
}
