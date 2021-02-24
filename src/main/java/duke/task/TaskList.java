package duke.task;

import duke.command.Duke;
import duke.command.DukeExceptions;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return (taskList.size() == 0);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(Integer index) throws DukeExceptions {
        if (index > taskList.size()) {
            throw new DukeExceptions();
        }
        return taskList.get(index);
    }

    public void appendTask(Task newTask) {
        taskList.add(newTask);
    }

    public void markIsCompletedTask(Integer index, boolean isCompleted) {
        Task currentTask = taskList.get((int)index);
        currentTask.setIsCompleted(isCompleted);
    }

    public void deleteTask(Integer index) throws DukeExceptions{
        if (index > taskList.size()) {
            throw new DukeExceptions();
        }
        taskList.remove((int)index);
    }

    public Integer getSize() {
        return taskList.size();
    }
}
