import common.Constants;

public class Parser {

    private static final Constants constants = new Constants();
    private static final TaskManager taskManager = new TaskManager();

    private static boolean isEndProgram = false;


    /**
     * Returns end program status.
     */
    public boolean isEndProgramNow() {
        return isEndProgram;
    }


    /**
     * Processes input.
     *
     * @param input Input value by user.
     */
    public void processInput(String input) {
        if (input.equals("bye")) {
            //Ends program
            endProgram();
        } else if (input.equals("list")) {
            //Shows saved tasks
            taskManager.printList();
        } else if (input.equals("help")) {
            //Shows all commands
            System.out.println(constants.MESSAGE_HELP);
        } else if (input.startsWith("done ")) {
            //Marks item as done
            taskManager.setDoneStatus(input, true);
        } else if (input.startsWith("undo ")) {
            //Marks item as undone
            taskManager.setDoneStatus(input,false);
        } else if (input.startsWith(constants.COMMAND_TODO)) {
            //Adds new ToDoTask
            taskManager.addToDo(input);
        } else if (input.startsWith(constants.COMMAND_EVENT)) {
            //Adds new EventTask
            taskManager.addEvent(input);
        } else if (input.startsWith(constants.COMMAND_DEADLINE)) {
            //Adds new DeadlineTask
            taskManager.addDeadline(input);
        } else if (input.startsWith("delete ")) {
            //Delete specified tasks
            taskManager.deleteTasks(input);
        } else {
            //unrecognized command
            printError();
        }
    }


    /**
     * Ends while-loop to exit program.
     */
    private void endProgram() {
        System.out.println(constants.MESSAGE_BYE);
        isEndProgram = true;
    }


    /**
     * Prints error message
     */
    private void printError() {
        System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
    }
}
