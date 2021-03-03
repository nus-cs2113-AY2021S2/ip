package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }


    public void listTasks(){
        if (Task.taskCount == 0) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < Task.taskCount; i++) {
                System.out.printf("%d.%s\n", i+1, tasks.get(i).toString());
            }
        }
    }

    public int getTaskCount() {
        return Task.taskCount;
    }

    public void printNumTasks() {
        if (getTaskCount() == 0) {
            System.out.println("No tasks yet!");
        } else {
            System.out.println("You now have " + getTaskCount() + " tasks in your tasklist.");
        }
    }

    public void deleteTasks(ArrayList<Integer> indexes) {
        if (indexes.size() == 0) {
            return;
        }
        if (indexes.size() > 1) {
            System.out.println("Okay, I've deleted these tasks:");
        } else {
            System.out.println("Okay, I've deleted this task:");
        }
        for (Integer index : indexes) {
            System.out.println(tasks.get(index).toString());
            Task.taskCount -= 1;
            tasks.remove(index.intValue());
        }
        printNumTasks();
    }

    public void markTasksAsDone(ArrayList<Integer> indexes) {
        for (Integer index : indexes) {
            tasks.get(index).markAsDone();
        }
    }
    public void addTask(Task newTask) {
        tasks.add(Task.taskCount-1, newTask); //taskCount was incremented before adding to tasklist
        System.out.println("I have added this task:" );
        System.out.println(tasks.get(Task.taskCount-1).toString());
        printNumTasks();
    }
}
