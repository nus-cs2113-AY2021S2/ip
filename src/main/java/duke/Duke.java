package duke;

import duke.task.*;
import duke.task.Event;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char INPUT_COMMENT_MARKER = '#';
    private TaskManager tasks;
    private static String filePath;
    private Storage storage;
    private Ui ui;


    public static CommandType getCommandType(String userInputString) {
        CommandType commandType;
        if(userInputString.equalsIgnoreCase("LIST")) {
            commandType = CommandType.LIST;
        } else if (userInputString.toUpperCase().matches("^(DONE).*$")) {
            commandType = CommandType.DONE;
        } else if (userInputString.toUpperCase().matches("^(DELETE).*$")) {
            commandType = CommandType.DELETE;
        }else if (userInputString.toUpperCase().matches("^(TODO).*$")) {
            commandType = CommandType.TODO;
        } else if (userInputString.toUpperCase().matches("^(DEADLINE).*$")) {
            commandType = CommandType.DEADLINE;
        } else if (userInputString.toUpperCase().matches("^(EVENT).*$")) {
            commandType = CommandType.EVENT;
        } else if (userInputString.equalsIgnoreCase("BYE")) {
            commandType = CommandType.EXIT;
        } else {
            commandType = CommandType.UNDEFINED;
        }
        return commandType;
    }


    public void executeAddTodo(String userCommand) throws EmptyDescriptionException, IOException {
        String[] typeContent = userCommand.split("[Tt][Oo][Dd][Oo]",2);
        if (typeContent[1].equals("")) {
            throw new EmptyDescriptionException(CommandType.TODO);
        }

        Todo t = tasks.addTodo(typeContent[1].trim());
        ui.showAddResult(t, tasks.getNumOfTasks());
        storage.writeToTxt(tasks.getTasks());
    }

    public void executeAddDeadline(String userCommand) throws EmptyDescriptionException, IOException {
        try {
            String[] typeContentBy = userCommand.trim().split("[Dd][Ee][Aa][Dd][Ll][Ii][Nn][Ee]", 2);
            String[] contentBy = typeContentBy[1].trim().split("/[Bb][Yy]", 2);
            if (contentBy[0].trim().equals("") || contentBy[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.DEADLINE);
            }
            Deadline d = tasks.addDeadline(contentBy[0].trim(), contentBy[1].trim());
            ui.showAddResult(d, tasks.getNumOfTasks());
            storage.writeToTxt(tasks.getTasks());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showExecuteResult("OOPS!!! No /by founded in the command");

        }

    }

    public void executeAddEvent(String userCommand) throws EmptyDescriptionException, IOException {
        try {
            String[] typeContentAt= userCommand.trim().split("[Ee][Vv][Ee][Nn][Tt]", 2);
            String[] contentAt = typeContentAt[1].trim().split("/[Aa][Tt]", 2);
            if (contentAt[0].trim().equals("") || contentAt[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.EVENT);
            }
            Event e = tasks.addEvent(contentAt[0].trim(), contentAt[1].trim());
            ui.showAddResult(e, tasks.getNumOfTasks());
            storage.writeToTxt(tasks.getTasks());
        } catch (IndexOutOfBoundsException e){
            ui.showExecuteResult("OOPS!!! No /at founded in the command");
        }

    }

    public void executeList(String userCommand) {
        tasks.listAllTasks();
    }

    public void executeDone(String userCommand) throws EmptyDescriptionException, IOException {
        try{
            String[] typeIndex = userCommand.split("[Dd][Oo][Nn][Ee]",2);
            int taskIndexShow = Integer.parseInt(typeIndex[1].trim());
            if(taskIndexShow <= 0 || taskIndexShow > tasks.getNumOfTasks()) {
                throw new EmptyDescriptionException(CommandType.DONE);
            }
            tasks.markTaskDone(taskIndexShow);
            storage.writeToTxt(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException(CommandType.DONE);
        }
    }

    public void executeDELETE(String userCommand) throws EmptyDescriptionException, IOException {
        try{
            String[] typeIndex = userCommand.split("[Dd][Ee][Ll][Ee][Tt][Ee]",2);
            int taskIndexShow = Integer.parseInt(typeIndex[1].trim());
            if(taskIndexShow <= 0 || taskIndexShow > tasks.getNumOfTasks()) {
                throw new EmptyDescriptionException(CommandType.DELETE);
            }
            tasks.deleteTask(taskIndexShow);
            storage.writeToTxt(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException(CommandType.DELETE);
        }
    }

    private void executeExitProgramRequest() {
        ui.showBye();
        System.exit(0);
    }

    public void executeCommand(String userInputString) throws IOException {
        CommandType type = getCommandType(userInputString);
        try {
            switch (type) {
            case TODO:
                executeAddTodo(userInputString);
                return;
            case DEADLINE:
                executeAddDeadline(userInputString);
                return;
            case EVENT:
                executeAddEvent(userInputString);
                return;
            case LIST:
                executeList(userInputString);
                return;
            case DONE:
                executeDone(userInputString);
                return;
            case DELETE:
                executeDELETE(userInputString);
                return;
            case EXIT:
                executeExitProgramRequest();
                return;
            default:
                ui.showMessageForInvalidCommandInput();
                return;
            }
        } catch (EmptyDescriptionException e) {
            e.showMessage();
            return;
        }

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = new TaskManager(storage.load());
    }

    public void run() throws IOException {
        ui.showHello();
        while (true) {
            String userCommand = ui.getUserInput();
            executeCommand(userCommand);
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }



}
