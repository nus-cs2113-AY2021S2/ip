package duke.parser;

import duke.Command;
import duke.TaskList;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidCommandException;

public class Parser {
    public static Command processInput(TaskList taskList, String input) {
        Command command;
        try {
            command = getCommand(input);
            switch (command) {
            case TODO:
                command = taskList.addTask(input.replaceFirst("todo ", ""), Command.TODO);
                break;
            case DEADLINE:
                command = taskList.addTask(input.replaceFirst("deadline ", ""), Command.DEADLINE);
                break;
            case EVENT:
                command = taskList.addTask(input.replaceFirst("event ", ""), Command.EVENT);
                break;

            case DONE:
                try {
                    validateDescription(input, Command.DONE);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Done command needs task number!");
                    command = Command.ERROR;
                    break;
                }
                int taskNum = getTaskNum(input, Command.DONE);
                taskList.finishTask(taskNum - 1);
                break;
            case DELETE:
                try {
                    validateDescription(input, Command.DELETE);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Delete command needs task number!");
                    command = Command.ERROR;
                }
                break;
            case FIND:
                try {
                    validateDescription(input, Command.FIND);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Find command needs a description!");
                    command = Command.ERROR;
                }
                break;
            default:
                break;
            }
        } catch (InvalidCommandException e) {
            System.out.println("Wrong command entered!: " + input);
            command = Command.ERROR;
        }
        return command;
    }

    public static int getTaskNum(String input, Command command) {
        switch (command) {
        case DONE:
            return Integer.parseInt(input.replaceFirst("done ", ""));
        case DELETE:
            return Integer.parseInt(input.replaceFirst("delete ", ""));
        default:
            return -1;
        }
    }

    public static Command getCommand(String input) throws InvalidCommandException {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("done ")) {
            return Command.DONE;
        } else if (input.startsWith("todo ")) {
            return Command.TODO;
        } else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            return Command.EVENT;
        } else if (input.startsWith("delete ")) {
            return Command.DELETE;
        } else if (input.startsWith("find ")) {
            return Command.FIND;
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void validateDescription(String description, Command command) throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException();
        }
        switch (command) {
        case DONE:
            if (description.replace("done ", "").equals("")) {
                throw new EmptyDescriptionException();
            }
            break;
        case DELETE:
            if (description.replace("delete ", "").equals("")) {
                throw new EmptyDescriptionException();
            }
            break;
        case FIND:
            if (description.replace("find ", "").equals("")) {
                throw new EmptyDescriptionException();
            }
            break;
        default:
            break;
        }
    }
}
