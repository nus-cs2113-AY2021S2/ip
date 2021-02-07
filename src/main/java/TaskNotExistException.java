public class TaskNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "This task doesn't exist! Please choose a valid task!";
    }
}
