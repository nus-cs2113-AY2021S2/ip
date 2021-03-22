package dukchess.commands;

import java.util.List;

import dukchess.entity.Task;
import dukchess.entity.TaskList;

public abstract class Command {
    protected static List<Task> tasks = TaskList.getTasksList();
}
