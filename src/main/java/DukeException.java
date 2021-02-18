public class DukeException {
    private static final String WARNING_DIVIDER = "\txxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";

    protected static void addTaskInvalidDescription() {
        System.out.print(WARNING_DIVIDER
                + "Invalid description! add description cannot be empty!\n"
                + WARNING_DIVIDER);
    }

    protected static void toDoInvalidDescription() {
        System.out.print(WARNING_DIVIDER
                + "Invalid description! todo description cannot be empty!\n"
                + WARNING_DIVIDER);
    }

    protected static void deadlineInvalidDescription() {
        System.out.print(WARNING_DIVIDER
                + "Invalid description! deadline cannot be empty!\n"
                + WARNING_DIVIDER);
    }

    protected static void eventInvalidDescription() {
        System.out.print(WARNING_DIVIDER
                + "Invalid description! event cannot be empty!\n"
                + WARNING_DIVIDER);
    }

    protected static void invalidCommand() {
        System.out.print(WARNING_DIVIDER
                + "Please enter a valid command!\n"
                + WARNING_DIVIDER);
    }

    protected static void invalidTask(){
        System.out.print(WARNING_DIVIDER
                + "Please enter a valid task!\n"
                + WARNING_DIVIDER);
    }

}
