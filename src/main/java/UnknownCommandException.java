public class UnknownCommandException extends Throwable {
    @Override
    public String getMessage() {
        return "____________________________________________________________\n" +
                "I'm sorry, but I don't know what that means. :(\n" +
                "Please enter a valid command. :)\n" +
                "____________________________________________________________\n";
    }
}
