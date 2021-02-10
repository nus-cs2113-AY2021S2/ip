public class TodoException extends Exception {
    private String errorMessage;

    public TodoException() {
        this.errorMessage = "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    public String sendErrorMessage() {
        return this.errorMessage;
    }

}


