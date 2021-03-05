public class Ui {
    /***
     * Shows the welcome message.
     */
    public static void welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n");
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println("____________________________________________________________\n");
    }

    /***
     * Shows the termination of the application.
     */
    public static void bye() {
        System.out.println("____________________________________________________________\n");
        System.out.println(" Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }

    /***
     * Shows an error message when the task type is not recognised.
     */
    public static void showException() {
        System.out.println("____________________________________________________________\n");
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        System.out.println("____________________________________________________________\n");
    }

    /***
     * Shows an error message when the description of the task is empty.
     * @param line Task type.
     */
    public static void showEmptyDescriptionException(String line){
        System.out.println("____________________________________________________________\n");
        System.out.println("☹ OOPS!!! The description of a " + line + " cannot be empty.\n");
        System.out.println("____________________________________________________________\n");
    }


}
