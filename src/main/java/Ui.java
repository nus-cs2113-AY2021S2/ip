public class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Here are the things that i can do: ");
        System.out.println("Add task to your list - todo, deadline and event tasks");
        System.out.println("list: View all tasks in the list");
        System.out.println("done: Done task and delete: Delete task");
        System.out.println("bye: quite Duke");
        System.out.println("Please enter the task you want to add in to the task list.");
    }

    /**
     * send Good-bye message when user exit the program
     */
    public void showExitMessage() {
        System.out.println("Thank you for using Duke function, " +
                "Hope to see you again :)");
    }

    /**
     * Send error message when user entered invalid command
     */
    public void showErrorMessage() {
        System.out.println(":( OOps!! I'm sorry, " +
                "but i don't know what that means...");
    }

    /**
     * Send StringIndexOutOfBoundsException error message when todo task is added without description
     */
    public void todoIndexError() {
        System.out.println(":( OOps!! The description of a" +
                "todo cannot be empty");
    }

    /**
     * Send StringIndexOutOfBoundsException error message when event task is added without description
     */
    public void eventIndexError() {
        System.out.println(":( OOps!! The description of a" +
                "event cannot be empty");
    }

    /**
     * Send StringIndexOutOfBoundsException error message when deadline task is added without description
     */
    public void deadlineIndexError() {
        System.out.println(":( OOps!! The description of a" +
                "deadline cannot be empty");
    }

    /**
     * Send StringIndexOutOfBoundsException error message when user didn't enter which task index
     * to be mark as done
     */
    public void doneIndexError() {
        System.out.println(":( OOps!! The index of task to be done" +
                "cannot be empty");
    }

    /**
     * Send StringIndexOutOfBoundsException error message when user didn't enter which task index
     * to be deleted
     */
    public void deleteIndexError() {
        System.out.println(":( OOps!! The index of task to be deleted" +
                "cannot be empty");
    }

    /**
     * Send FileNotFoundException error message whe user add a task
     */
    public void fileErrorMessage() {
          System.out.println(":( OOps!! The file you are looking for does"+
                  "not exist...");
    }

    /**
     * Send IOException error message when when the program successfully opened
     * the file, but is unable to read the duke.txt file
     */
    public void exceptionMessage() {
        System.out.println(":( OOps!! Something is wrong when reading the file...");
    }
}
