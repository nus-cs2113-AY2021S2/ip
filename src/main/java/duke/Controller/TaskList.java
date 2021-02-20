package duke.controller;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.toDo;

public class TaskList {

    public TaskList() {}

    public void printTaskList(ArrayList<Task> tasks) {
        int number = 1;
        System.out.println("Here are the tings in yo list: ");
        for (Task task : tasks) {
            System.out.println(number + ". " + task.printDescription());
            number++;
        }
    }

    public void showDone(ArrayList<Task> tasks, String input) {
        int indexSpace = input.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("I see you forget how to type numbers yea... you donut! Type in your task number man!");
        } else {
            String numberString = input.substring(indexSpace + 1);
            int taskNumber = Integer.parseInt(numberString);
            try {
                if (taskNumber > tasks.size() || taskNumber == 0) {
                    throw new NullPointerException("Are you blind? There is no such task number you fool!");
                }
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println("Awwww yeah! I've marked this task as done... brrrrrap brrrrrap: ");
                System.out.println(tasks.get(taskNumber - 1).printDescription());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteTask(ArrayList<Task> tasks, String input) {
        int indexSpace = input.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("I see you forget how to type numbers yea... you donut! Type in your task number man!");
        } else {
            String numberString = input.substring(indexSpace + 1);
            int taskNumber = Integer.parseInt(numberString);
            try {
                if (taskNumber > tasks.size() || taskNumber == 0) {
                    throw new NullPointerException("Are you blind? There is no such task number you fool!");
                }
                System.out.println("Awwww yeah! I've deleted this task like a beast: ");
                System.out.println(tasks.get(taskNumber - 1).printDescription());
                tasks.remove(taskNumber - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printToDo(ArrayList<Task> tasks, String input, String description) {
        Task todo = new toDo(description);
        tasks.add(todo);
        System.out.println("Ayy I got you my brother. I've added this ting: ");
        System.out.println(todo.printDescription());
        System.out.println("Dayuum son! You have " + tasks.size() + " mad tings in the list.");
    }

    public void printDeadline(ArrayList<Task> tasks, String input, String description, String date) {
        try{
            Task deadline = new Deadline(description, date);
            tasks.add(deadline);
            System.out.println("Ayy I got you my brother. I've added this ting: ");
            System.out.println(deadline.printDescription());
            System.out.println("Jeeeeeeez! You have " + tasks.size() + " mad tings in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Oi you gotta key input in specified format dd-MM-yyyy HH:mm");
        }
    }

    public void printEvent(ArrayList<Task> tasks, String input, String description, String date) {
        try {
            Task event = new Event(description, date);
            tasks.add(event);
            System.out.println("Ayy I got you my brother. I've added this ting: ");
            System.out.println(event.printDescription());
            System.out.println("I feer! You have " + tasks.size() + " mad tings in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Oi you gotta key input in specified format dd-MM-yyyy HH:mm!");
        }
    }

    public void findTask(ArrayList<Task> tasks, String keyword) {
        boolean isFound = false;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword) && !isFound) {
                System.out.print("Here are the search results: \n");
                System.out.println(task.printDescription());
                isFound = true;
            } else {
                System.out.println("No such task!");
            }
        }
    }

}