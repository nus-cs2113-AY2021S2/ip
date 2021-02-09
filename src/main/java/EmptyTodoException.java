public class EmptyTodoException extends Throwable{
    @Override
    public String getMessage() {
        return "____________________________________________________________\n" +
                "The description of a todo cannot be empty!\n" +
                "____________________________________________________________\n";
    }
}
