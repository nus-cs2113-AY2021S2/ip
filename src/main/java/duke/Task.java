package duke;

import java.util.ArrayList;

public class Task {

    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return "";
    }
    public String getStatusIcon(boolean isDone) {
        return (isDone ? "[\u2713] " : "[ ] ");
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon(isDone) + description;
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
    }

    public static void markTaskDone(ArrayList<Task> tasks, String userInput) {
        int processedInput;
        processedInput = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        tasks.get(processedInput).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(processedInput));
        //Storage.writeToFile(tasks);
    }

    public static void addTodoTask(ArrayList<Task> tasks, String userInput) {
        System.out.println("Got it. I've added this task: ");
        Task newTask = new Todo(userInput.substring(5));
        tasks.add(newTask);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        //Storage.writeToFile(tasks);
    }

    public static void addDeadlineTask(String by, ArrayList<Task> tasks,
                                       String userInput, int getSlashIndex) {
        String processedDeadlineInput;
        by = userInput.substring(getSlashIndex + 4);
        processedDeadlineInput = userInput.substring(9, getSlashIndex).trim();
        Task newTask = new Deadline(processedDeadlineInput, by);
        tasks.add(newTask);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}

