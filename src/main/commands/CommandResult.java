package main.commands;

import main.TaskList;

/**
 * Represents the result of a command execution.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user. Contains a description of the execution result
     */
    public final String feedbackToUser;

    /**
     * The task list that was produced by the command
     */
    public final TaskList taskList;
    /**
     * The task list that matches the keyword defined by user
     */
    public TaskList matchedTaskList;
    public String commandWord;

    public CommandResult(String feedbackToUser, TaskList taskList) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = taskList;
        this.commandWord = null;
    }

    public CommandResult(String feedbackToUser, TaskList taskList, String commandWord) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = taskList;
        this.commandWord = commandWord;
    }

    public CommandResult(String feedbackToUser, String commandWord, TaskList taskList, TaskList matchedTaskList) {
        this.feedbackToUser = feedbackToUser;
        this.taskList = taskList;
        this.matchedTaskList = matchedTaskList;
        this.commandWord = commandWord;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
