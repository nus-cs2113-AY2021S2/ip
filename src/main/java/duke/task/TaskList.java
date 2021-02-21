package duke.task;

import duke.ui.Ui;
import java.util.ArrayList;


/**
 * Implements the list of tasks.
 *
 * @author Leonardo Irvin Pratama
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;

    /**
     * Initializes an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Initializes a TaskList object.
     *
     * @param savedTasks Tasks gathered from save file.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
        this.taskCount = savedTasks.size();
    }

    /**
     * Returns user current tasks.
     *
     * @return User current tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     * @param ui   Ui to get responses.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        taskCount += 1;
        ui.accumulateResponses(" Your task has been recorded.",
                "\n   " + task,
                "\n You have " + taskCount + " tasks currently.");
    }

    /**
     * Deletes a specific task.
     *
     * @param index Position of the task to be deleted.
     * @param ui    Ui to get responses.
     */
    public void deleteTask(int index, Ui ui) {
        if (index < 1 || taskCount < index) {
            ui.accumulateResponses(" Sorry I cannot find your specified task!");
        } else {
            assert taskCount >= index : " Index must not be greater than task count";
            Task removed = tasks.get(index - 1);
            tasks.remove(index - 1);
            taskCount -= 1;
            ui.accumulateResponses(" Removing this task,",
                    "\n   " + removed,
                    "\n You have " + taskCount + " tasks currently.");
        }
    }

    /**
     * Lists all tasks in task list.
     *
     * @param ui Ui to get responses.
     */
    public void listTasks(Ui ui) {
        if (taskCount == 0) {
            ui.accumulateResponses(" You've got no tasks now. Add some to keep you busy!");
        } else {
            assert taskCount > 0 : " Task list must not be empty!";
            ui.accumulateResponses(" Here are all your tasks: \n");
            for (int i = 0; i < taskCount; i++) {
                ui.accumulateResponses("   " + (i + 1) + "." + tasks.get(i) + "\n");
            }
        }
    }

    /**
     * Marks a specific task as done.
     *
     * @param index Position of the task to be completed.
     * @param ui    Ui to get responses.
     */
    public void markAsDone(int index, Ui ui) {
        if (index < 1 || taskCount < index) {
            ui.accumulateResponses(" I cannot find your specified task!");
        } else {
            assert taskCount >= index : " Index must not be greater than task count.";

            if (tasks.get(index - 1).getIsDone()) {
                ui.accumulateResponses(" Task is already marked as done!");
            } else {
                tasks.get(index - 1).markAsDone();
                ui.accumulateResponses(" Great job, one task down!",
                        "\n   " + tasks.get(index - 1));
            }
        }
    }
}
