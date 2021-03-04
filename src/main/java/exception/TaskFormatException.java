package exception;

public class TaskFormatException extends RuntimeException{
    protected final String PRE_SPACE = "    ";

    public TaskFormatException(){};

    @Override
    public String toString(){
        return "Duke accepts the following commands for adding a new task:\n" +
                "    1.todo <task name>\n" + "      Add a new todo task to task list\n      " +
                "Eg. todo do homework\n" + "    2.deadline <task name> /by <deadline>\n" +
                "      Add a new deadline to task list\n      Eg. deadline return a book /by tonight\n" +
                "    3.event <task name> /at <time>\n      Add a new event to task list\n" +
                "      Eg. event meeting /at 8pm";
    }
}
