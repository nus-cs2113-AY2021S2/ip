public class Command {
    private CommandEnum commandType;
    private String description;
    private String when;
    private Integer taskNum;

    public Command(String userInput){
        setCommandFromInput(userInput);
    }

    public CommandEnum getCommandType(){
        return commandType;
    }

    public String getDescription(){
        return description;
    }

    public String getWhen(){
        return when;
    }

    public Integer getTaskNum(){
        return taskNum;
    }

    private void setCommandFromInput(String input){
        commandType = getCommandTypeFromInput(input);
//        Split user input by the first "/" to separate description and second argument (at or by, if applicable).
        String[] arguments = input.split("/", 2);
//        remove the command word (eg. deadline, event, etc.) from the description.
        description = arguments[0].substring(arguments[0].indexOf(' ')+1);
        if (commandType == CommandEnum.DEADLINE || commandType == CommandEnum.EVENT) {
//            remove the '/by' or '/at' from the 'when' argument
            when = arguments[1].substring(arguments[1].indexOf(' ')+1);
        }
        if (commandType == CommandEnum.DONE) {
//            Split user input by " " to separate 'done' and task number according to the format.
            try {
                String taskNumArgument = input.split(" ")[1];
                taskNum = Integer.parseInt(taskNumArgument);
            } catch (ArrayIndexOutOfBoundsException indexError) {
                System.out.println("Error -> Task number cannot be left empty.");
            }
        }
    }

    private CommandEnum getCommandTypeFromInput(String input) {
        if (input.equals("bye")) {
            return CommandEnum.BYE;
        } else if (input.equals("list")) {
            return CommandEnum.LIST;
        } else if (input.contains("done")) {
            return CommandEnum.DONE;
        } else if (input.contains("deadline") && input.contains("/by")) {
            return CommandEnum.DEADLINE;
        } else if (input.contains("event") && input.contains("/at")) {
            return CommandEnum.EVENT;
        } else if (input.contains("todo")) {
            return CommandEnum.TODO;
        }
        return CommandEnum.INVALID;
    }
}
