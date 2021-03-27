package dukchess.commands;

import java.util.List;

import dukchess.entity.Task;
import dukchess.entity.TaskList;

/**
 * Base class for all commands so each have a reference to list of tasks
 */
public abstract class Command {
    protected static List<Task> tasks = TaskList.getTasksList();
}
