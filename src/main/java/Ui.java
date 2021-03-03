/**
 * Manages the text ui interface
 */

public class Ui {

    public static final String LINE_STRING = "____________________________________________________________";

    private void printLine() {
        System.out.println(LINE_STRING);
    }

    public void printAddToList(int size, String status) {
        printLine();
        System.out.println("Say no more fam. The task is added:\n  " + status);
        System.out.println(size + " task(s) in the list.");
        printLine();
    }

    public void printList(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i+1) + "." + tasks.getStatus(i));
        }
        printLine();
    }

    public void printCompleteTask(String status) {
        printLine();
        System.out.println("Task.Task marked as done, gg ez");
        System.out.println("  " + status);
        printLine();
    }

    public void printDeleteTask(String status, int size) throws IndexOutOfBoundsException {
        printLine();
        System.out.println("You are a quitter 👎 Anyways, I removed this:");
        System.out.println("  " + status);
        System.out.println(size + " task(s) left in the list.");
        printLine();
    }

    public void printFindTask(TaskList results, String query) {
        printLine();
        System.out.println("Here are the tasks in your list that match「" + query + "」:");
        for (int i = 0; i < results.getSize(); i++) {
            System.out.println((i+1) + "." + results.getStatus(i));
        }
        printLine();
    }

    public void printNoResultsFound() {
        printLine();
        System.out.println("No tasks found!");
        printLine();
    }

    public void printWelcome() {
        String welcome = LINE_STRING +
                "\n Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n" +
                LINE_STRING;
        System.out.println(welcome);
    }

    public void printGoodbye() {
        String goodbye = LINE_STRING + "\n Chao 👌\n" + LINE_STRING;
        System.out.println(goodbye);
    }

    public void printNoCommandFormat(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                "\n 😥 You gotta use the time marker for " + commandName + "\n" +
                "and the time which it happens\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNoTaskLabel(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                "\n 😥 You gotta tell me what is the task for " + commandName + "\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNoSuchMethod() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 I don't quite get what the command means, \n" +
                "type 'help' for a list of commands\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printInputMismatch() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 There is some issue with getting you input\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printIOException() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 IO issue encountered! Unable to read file\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNumberFormatException() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Number format exception encountered! Input may be corrupted\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNoTaskSpecified() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Tell me what task are you referring to\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printIndexOutOfBounds() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Index out of bounds\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printNoSearchQuery() {
        String exceptionMessage = LINE_STRING +
                "\n 😥 Please tell me what you are finding for\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printHelp() {
        String helpMessage = LINE_STRING +
                "\n Available commands:"
                + "\n   todo taskLabel"
                + "\n   deadline deadlineLabel /by time"
                + "\n   event eventLabel /at time"
                + "\n   list"
                + "\n   done taskNumber"
                + "\n   find query"
                + "\n   delete taskNumber"
                + "\n   help"
                + "\n   bye\n"
                + LINE_STRING;
        System.out.println(helpMessage);
    }
}
