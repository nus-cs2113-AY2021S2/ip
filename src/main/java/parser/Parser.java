package parser;

import commands.*;
import common.Constants;

public class Parser {

    private static final Constants constants = new Constants();

    private static boolean isEndProgram = false;


    /**
     * Returns end program status.
     */
    public boolean isEndProgramNow() {
        return isEndProgram;
    }


    /**
     * Processes input and executes command.
     *
     * @param input Input value by user.
     */
    public void processInput(String input) {
        if (input.equals(constants.COMMAND_QUIT)) {
            //Ends program
            endProgram();
        } else if (input.equals(constants.COMMAND_LIST)) {
            //Shows saved tasks
            new PrintListCommand().execute();
        } else if (input.equals(constants.COMMAND_HELP)) {
            //Shows all commands
            System.out.println(constants.MESSAGE_HELP);
        } else if (input.startsWith(constants.COMMAND_DONE)) {
            //Marks item as done
            new SetDoneStatusCommand(input, true).execute();
        } else if (input.startsWith(constants.COMMAND_UNDO)) {
            //Marks item as undone
            new SetDoneStatusCommand(input,false).execute();
        } else if (input.startsWith(constants.COMMAND_TODO)) {
            //Adds new ToDoTask
            new AddToDoCommand(input).execute();
        } else if (input.startsWith(constants.COMMAND_EVENT)) {
            //Adds new EventTask
            new AddEventCommand(input).execute();
        } else if (input.startsWith(constants.COMMAND_DEADLINE)) {
            //Adds new DeadlineTask
            new AddDeadlineCommand(input).execute();
        } else if (input.startsWith(constants.COMMAND_DELETE)) {
            //Delete specified tasks
            new DeleteTaskCommand(input).execute();
        } else if (input.startsWith(constants.COMMAND_FIND)) {
            //Find tasks matching keyword
            new FindCommand(input).execute();
        } else {
            //unrecognized command
            printError();
        }
    }


    /**
     * Sets isEndProgram as true to end while-loop and exit program.
     */
    private void endProgram() {
        System.out.println(constants.MESSAGE_BYE);
        isEndProgram = true;
    }


    /**
     * Prints error message.
     */
    private void printError() {
        System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
    }
}
