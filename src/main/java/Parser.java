public class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";


    public static Command parse(String input) {
        String[] command = getCommand(input);

        switch (command[0]) {
            case BYE:
                return new ExitCommand(input);
            case LIST:
                return new ListCommand(input);
            case DONE:
                return new DoneCommand(input);
            default:
                return new AddCommand(input);
        }
    }

    private static String[] getCommand(String input) { String[] command = input.trim().split(" ");
        return command;
    }
}

