package duke.exception;

public class DukeException extends Exception{
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public DukeException(String message) {
        super(message);
    }

    public String getEmptyListMessage(){
        return "\t:( OOPS!!! You haven't noted down anything yet.";
    }

    public String getTaskAlreadyDoneMessage(){
        return "\tThe task is already done. :)";
    }

    public String getIllegalKeywordMessage() {
        return "\t:( OOPS!!! I'm sorry, but I don't know what that means.";
    }

    public String getIllegalTodoMessage(){
        return "\t:( OOPS!!! The description of a todo cannot be empty.";
    }

    public String getIllegalDeadlineMessage(){
        return "\t:( OOPS!!! You are not specifying a valid deadline with time.";
    }

    public String getIllegalEventMessage() {
        return "\t:( OOPS!!! You are not specifying a valid event with venue.";
    }
}
