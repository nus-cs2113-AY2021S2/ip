package duke.commands;

import duke.exception.EmptyCommandArgException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandTimeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.TaskList;
import duke.util.Ui;

public abstract class Command {
    protected static Ui ui;
    protected String commandArg;
    protected boolean isExit;

    public Command() {
        ui = new Ui();
        isExit = false;
    }

    public Command(String commandArgString) {
        ui = new Ui();
        commandArg = commandArgString;
        isExit = false;
    }

    public abstract void execute(TaskList taskList, Ui ui) throws EmptyCommandArgException,
            InvalidCommandTimeException, InvalidTaskNumberException, InvalidCommandException;

    public boolean isEmptyArgument(String commandArg) {
        return commandArg.length() == 0;
    }

    public int getTaskNumber(String commandArg, TaskList taskList) throws InvalidTaskNumberException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(commandArg);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(commandArg);
        }

        if (taskNumber < 1 || taskNumber > taskList.getListSize()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        return taskNumber;
    }

    public String[] splitCommandArg(String commandType, String commandArg) throws InvalidCommandTimeException {
        String[] taskDescriptionAndTime;
        String delimiter = null;
        switch (commandType) {
        case "deadline":
            delimiter = "/by";
            break;
        case "event":
            delimiter = "/at";
            break;
        }
        taskDescriptionAndTime = commandArg.split(delimiter, 2);
        if (taskDescriptionAndTime.length == 1 || taskDescriptionAndTime[1].equals("")) {
            throw new InvalidCommandTimeException(commandType);
        }
        taskDescriptionAndTime[0] = taskDescriptionAndTime[0].trim();
        taskDescriptionAndTime[1] = taskDescriptionAndTime[1].trim();

        return taskDescriptionAndTime;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }
}
