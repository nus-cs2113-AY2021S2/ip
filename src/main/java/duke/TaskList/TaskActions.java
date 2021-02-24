package duke.TaskList;

import duke.exceptions.EmptyListException;
import duke.exceptions.TaskDoneException;
import duke.task.Task;

import java.util.List;

public class TaskActions {

    /** Prints all tasks */
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

    /** Marks specific task as done */
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

    /** Deletes specific task */
    public void deleteTask(List<Task> tasks, int index) {
        tasks.remove(index);
        System.out.println("\tDone! One less worry for you :)");
    }

    /** Finds and prints all tasks with user specified keyword */
    public void findTask(List<Task> tasks, List<Task> findTasks, String word) {
        int i = 0;
        findTasks.clear();
        for (Task t : tasks) {
            if (t.getTask().contains(word)) {
                findTasks.add(t);
            }
        }
        if (findTasks.size() == 0) {
            System.out.println("\tNo matching tasks found!");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            while (i < findTasks.size()) {
                int num = i + 1;
                System.out.println("\t" + num + ". " + findTasks.get(i).toString());
                i++;
            }
        }
    }
}
