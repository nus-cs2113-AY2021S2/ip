public class DukeException extends Exception{

    protected String error;

    public DukeException(String error){
        this.error = error;
    }

    public void getError(String input) {
        switch (error) {
            case "done":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"done [task number]\" ");
                break;
            case "todo":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"todo [description]\"");
                break;
            case "deadline":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"deadline [description] /by [time]\"");
                break;
            case "event":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"event [description] /at [time]\"");
                break;
            case "delete":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"delete [task number]\" ");
                break;
            default:
                System.out.println("OOPS! Your command may not be valid! " +
                        "Please check the list of available commands using \"help\"");
        }
    }
}

// "list", "done", "todo", "deadline", "event", "delete"