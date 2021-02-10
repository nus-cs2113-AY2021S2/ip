package duke;

public class DukeException extends Exception{
    public String getEmptyListMessage(){
        return "\t:( OOPS!!! You haven't noted down anything yet.";
    }

    public String getTaskAlreadyDoneMessage(){
        return "\tThe task is already done. :)";
    }

    public String getIllegalKeywordMessage() {
        return "\t:( OOPS!!! I'm sorry, but I don't know what that means.";
    }
}
