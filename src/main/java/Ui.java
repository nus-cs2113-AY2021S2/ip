public class Ui {
    protected String filepath;
    public Ui (String filepath) {
        this.filepath = filepath;
    }

    public static void printDash() {
        System.out.println("-".repeat(80));
    }

    public String getFilePath() {
        return filepath;
    }

    /**
     * print welcome message
     */
    static void printGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printDash();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDash();
    }

    /**
     * print loading error message
     */
    public void showLoadingError() {
        System.out.println("\tUnable to access file at "+ getFilePath());
    }

    /**
     * print empty task number message
     */
    public static void emptyTaskNumberMessage() {
        System.out.println("\tOOPS!!! Please indicate task number");
    }

    /**
     * print list message
     */
    public static void validListMessage() {
        System.out.println("\ttHere are the tasks in your list: ");
    }

    /**
     * print empty list message
     */
    public static void emptyListMessage() {
        System.out.println("\tYou do not have any pending task.");
    }

    /**
     * print invalid command message
     */
    public static void invalidCommandMessage() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * print added task message
     */
    public static void addedTaskMessage() {
        System.out.println("\tGot it. I've added this task: ");
    }

    /**
     * print invalid task number message
     */
    public static void invalidTaskNumberMessage() {
        System.out.println("\tOOPS!!! Please enter valid task number");
    }

    /**
     * print completed task message
     */
    public static void taskIsDoneMessage() {
        System.out.println("\tNice! I've marked this task as done: ");
    }

}
