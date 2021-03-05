package duke;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
    }

    public static void markTaskDone(String userInput) {
        int processedInput;
        processedInput = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
        tasks.get(processedInput).setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(processedInput));
        //Storage.writeToFile(tasks);
    }

    public static void addTodoTask(String userInput) {
        System.out.println("Got it. I've added this task: ");
        Task newTask = new Todo(userInput.substring(5));
        tasks.add(newTask);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        //Storage.writeToFile(tasks);
    }

    public static void addDeadlineTask(String by, String processedDeadlineInput) {
        Task newTask = new Deadline(processedDeadlineInput, by);
        tasks.add(newTask);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addEventTask(String at, String processedEventInput) {
        Task newTask = new Event(processedEventInput, at);
        tasks.add(newTask);
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void findTask(String targetInput) {
        int count = 0;
        for(Task t : tasks) {
            String[] mySplit = t.toString().split(" ");
            for(String s: mySplit) {
                if(s.equals(targetInput)) {
                    count += 1;
                    System.out.println(count + ". " + t);
                }
            }
        }

    }


    public String getDescriptionAtIndex(int i) {
        return tasks.get(i).getDescription();
    }

//    public void getIndex(int i) {
//        tasks.get(i);
//    }
    public int size() {
        return tasks.size();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    public String getProcessedInputAtIndex(int processedInput) {
        return String.valueOf(tasks.get(processedInput));
    }
}
