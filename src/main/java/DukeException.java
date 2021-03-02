/**
 * Contains errors that duke may encounter during runtime
 */
public class DukeException extends Exception{

    protected String error;

    /**
     * Instantiates this exception
     *
     * @param error error type
     */
    public DukeException(String error){
        this.error = error;
    }

    /**
     * Shows the error encountered by the user
     *
     * @param input error type
     */
    public void getError(String input) {
        switch (error) {
            case "done":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"done [(integer) task number]\"" +
                        "Please make sure you do not include any brackets [ or ] in your input!");
                break;
            case "todo":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"todo [description]\"" +
                        "Please make sure you do not include any brackets [ or ] in your input!");
                break;
            case "deadline":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"deadline [description] /by [time]\"" +
                        "Please make sure you do not include any brackets [ or ] in your input!");
                break;
            case "event":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"event [description] /at [time]\"" +
                        "Please make sure you do not include any brackets [ or ] in your input!");
                break;
            case "delete":
                System.out.println("OOPS! I need the command to be in the right format! " +
                        "Try \"delete [(integer) task number]\"" +
                        "Please make sure you do not include any brackets [ or ] in your input!");
                break;
            case "loadFile":
                System.out.println("OOPS! The file format is wrong! It may have been corrupted!" +
                        "Please delete the file \"data/saveTaskList.txt\" to try making another file!");
            default:
                System.out.println("OOPS! Your command may not be valid! " +
                        "Please check the list of available commands using \"help\"");
                break;
        }
    }
}
