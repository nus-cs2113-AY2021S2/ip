public class TaskDescriptionMissingException extends Exception {
    @Override
    public String getMessage() {
        String errorMessage = "☹ OOPS!!! The description of a todo cannot be empty. \n" + "Please try again!";
        return errorMessage;
    }
}
