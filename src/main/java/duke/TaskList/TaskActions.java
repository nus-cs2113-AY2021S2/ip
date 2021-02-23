package duke.TaskList;

import duke.exceptions.EmptyListException;
import duke.exceptions.TaskDoneException;
import duke.task.Task;

import java.util.List;

public class TaskActions {

    public void listTasks(List<Task> tasks) {
        int i = 0;
        if (tasks.size() == 0) {
            System.out.println("\tWow! You have time on your hands! Go do something you enjoy :)");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            while (i < tasks.size()) {
                int num = i + 1;
                System.out.println("\t" + num + ". " + tasks.get(i).toString());
                i++;
            }
        }
    }

    public void markAsDone(List<Task> tasks, int index) throws EmptyListException, TaskDoneException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        } else if (tasks.get(index).isDone()) {
            throw new TaskDoneException();
        } else {
            tasks.get(index).markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + tasks.get(index).toString());
        }
    }

    public void deleteTask(List<Task> tasks, int index) {
        tasks.remove(index);
        System.out.println("\tDone! One less worry for you :)");
    }
}
