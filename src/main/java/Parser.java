/**
 * To check user's input is valid
 */
public class Parser {

    public Parser(){};
    public static Ui ui = new Ui();

    /**
     * Check the list command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isList(String user_input) {
        if (user_input.equals("list")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the todo command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isTodo(String user_input) {
        if (user_input.length() > 4) {
            if (user_input.substring(0, 4).equals("todo")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the done command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isDone(String user_input) {
        if (user_input.length() > 4) {
            if (user_input.substring(0, 4).equals("done")) {
                return true;
            }
        }
        return false;
    }


    private static boolean emptyInput(String user_input, Command command) {
        switch (command) {
            case TODO:
                return (user_input.substring(ui.TODO_START, user_input.length()).strip().equals(""));
            case EVENT:
                return user_input.substring(ui.EVENT_START, getDeadlineIndex(user_input)).strip().equals("");
            case DEADLINE:
                return (user_input.substring(ui.DEADLINE_START, getDeadlineIndex(user_input)).strip().equals(""));
        }
        return false;
    }
    /**
     * Check the delete command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isDelete(String user_input) {
        if (user_input.length() > 6) {
            if (user_input.substring(0, 6).equals("delete")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the event command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
   public static boolean isEvent(String user_input) {
        if (user_input.length() > 5) {
            if (user_input.substring(0, 5).equals("event")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the deadline command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isDeadline(String user_input) {
        if (user_input.length() > 8) {
            if (user_input.substring(0, 8).equals("deadline")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the index of the task that the user inputs as done
     * @param user_input
     * @return the index
     */
    public static int getDoneTaskIndex(String user_input) {
        int taskIndex = Integer.parseInt(user_input.substring(ui.DONE_START, user_input.length()));
        return taskIndex;
    }
    /**
     * Check the bye command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isBye(String user_input) {
        if (user_input.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the find command is proper
     * @param user_input
     * @return true if it is valid and false otherwise
     */
    public static boolean isFind(String user_input){
        if (user_input.length() > 4) {
            if (user_input.substring(0, 4).equals("find")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the index of the time specified
     * @param user_input
     * @return the index of the time
     */
    public static int getDeadlineIndex(String user_input) {
        int index = user_input.indexOf('/');
        return index;
    }

    /**
     * Get the deadline of the task
     * @param user_input
     * @return string deadline of the task. e.g. 4pm.
     */
    public static String getDeadline(String user_input) {
        int timeIndex = getDeadlineIndex(user_input);
        String deadline = user_input.substring(timeIndex + 3, user_input.length());
        return deadline;
    }

    /**
     * Check whether the input event time is valid
     * @param user_input
     * @return true if it is valid and false otherwise
     * @throws InvalidEventException
     */
    protected static boolean validEventTime(String user_input) throws InvalidEventException {
        if (user_input.substring(getDeadlineIndex(user_input), getDeadlineIndex(user_input) + 3).equals("/at")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check whether the input deadline time is valid
     * @param user_input
     * @return true if it is valid and false otherwise
     * @throws InvalidDeadlineException
     */
    protected static boolean validDeadlineTime(String user_input) throws InvalidDeadlineException {
        if (user_input.substring(getDeadlineIndex(user_input), getDeadlineIndex(user_input) + 3).equals("/by")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check whether the input is valid
     * @param user_input
     * @param command
     * @throws EmptyInputException
     * @throws InvalidEventException
     * @throws InvalidDeadlineException
     */
    protected static void checkValidInput(String user_input, Command command) throws EmptyInputException, InvalidEventException, InvalidDeadlineException {
        if (emptyInput(user_input, command)) {
            throw new EmptyInputException();
        }

        switch (command) {
            case EVENT:
                if (validEventTime(user_input)) {
                    break;
                } else {
                    throw new InvalidEventException();
                }
            case DEADLINE:
                if (validDeadlineTime(user_input)) {
                    break;
                } else {
                    throw new InvalidDeadlineException();
                }
        }
    }


}
