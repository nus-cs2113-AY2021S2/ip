import java.time.LocalDate;

/**
 * parse input as commands
 */
public class Parser {

    /**
     * parese inputs as different commands
     * @param input input by user
     * @return returns a command
     * @throws DukeException exceptions found in the input
     */
    public static Command parse(String input) throws DukeException{
        String[] commandline = input.split(" ");
        Command command;
        int taskIndex;
        switch (commandline[0]) {
            case "list":
                if(input.length() == 4) {
                    command = new ListCommand();
                }else{
                    throw new DukeException("☹ Sorry! You cannot type other things except for 'list'!");
                }
                break;
            case "done":
                taskIndex = Integer.parseInt(commandline[1]);
                command = new DoneCommand(taskIndex);
                break;
            case "delete":
                taskIndex = Integer.parseInt(commandline[1]);
                command = new DeleteCommand(taskIndex);
                break;
            case "todo":
                if(input.length() == 4){
                    throw new DukeException("☹ Sorry! You cannot leave descriptions of a todo empty");
                } else {
                    command = new AddCommand(new Todo(input.substring(5)));
                }
                break;
            case "deadline":
                if(input.length() == 8){
                    throw new DukeException("☹ Sorry! You cannot leave descriptions of a deadline empty");
                } else {
                    command = new AddCommand(new Deadline(input.substring(9, input.indexOf('/') - 1),
                            LocalDate.parse(input.substring(input.indexOf('/') + 4))));
                }
                break;
            case "event":
                if(input.length() == 5){
                    throw new DukeException("☹ Sorry! You cannot leave descriptions of an event empty");
                } else {
                    command = new AddCommand(new Event(input.substring(6, input.indexOf('/') - 1),
                            LocalDate.parse(input.substring(input.indexOf('/') + 4))));
                }
                break;
            case "find":
                if(input.length() == 4){
                    throw new DukeException("☹ Sorry! You cannot find an empty task");
                } else {
                    command = new FindCommand(input.substring(5));
                }
                break;
            case "bye":
                command = new ExitCommand();
                break;

            default:
                throw new DukeException("☹ OOPS! I don't know what that means");
        }
        return command;
    }
}