package ManagerClasses;

import ExceptionClasses.SaveFileNotCreatedException;
import TaskClasses.Deadline;
import TaskClasses.Event;
import TaskClasses.Task;
import TaskClasses.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("Oops, it seems like you don't have any tasks.");
        } else {
            int taskId = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task task: tasks) {
                System.out.println(taskId + ". " + task.toString());
                taskId++;
            }
        }
    }

    public void markDone(Integer taskIdNum) {
//        Check if user input for 'done' task id is within the range of the list.
        try {
            if (taskIdNum != null) {
                int taskListIndexNum = taskIdNum - 1;
                Task task = tasks.get(taskListIndexNum);
                task.setAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task.toString());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error -> Cannot find task with the specified task number " + taskIdNum + ".");
        }
    }

    public void deleteTask (Integer taskIdNum) {
        try {
            if (taskIdNum != null) {
                int taskListIndexNum = taskIdNum - 1;
                Task task = tasks.get(taskListIndexNum);
                String taskDescription = task.toString();
                tasks.remove(taskListIndexNum);
                System.out.println("Nice! I've removed this task:");
                System.out.println(taskDescription);
                printNumberOfTasks();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error -> Cannot find task with the specified task number " + taskIdNum + ".");
        }
    }

    public void addTodoToList(String description) {
        Todo todoTask = new Todo(description);
        addTaskToList(todoTask);
    }

    public void addEventToList(String description, String at) {
        Event eventTask = new Event(description, at);
        addTaskToList(eventTask);
    }

    public void addDeadlineToList(String description, String by) {
        Deadline deadlineTask = new Deadline(description, by);
        addTaskToList(deadlineTask);
    }

    private void addTaskToList(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printNumberOfTasks();
    }

    public void printNumberOfTasks() {
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public void saveTasksInTxt() {
        String currentPath = System.getProperty("user.dir");
        java.nio.file.Path filePath = java.nio.file.Paths.get(currentPath, "data", "duke.txt");
        try (PrintStream out = new PrintStream(new FileOutputStream(String.valueOf(filePath)))) {
            for (Task task: tasks) {
                out.print(task.toSaveFormat() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void importTasksFromTxt() {
        String currentPath = System.getProperty("user.dir");
        java.nio.file.Path folderPath = java.nio.file.Paths.get(currentPath, "data");
        java.nio.file.Path filePath = java.nio.file.Paths.get(currentPath, "data", "duke.txt");
        boolean directoryExists = java.nio.file.Files.exists(folderPath);
        if (!directoryExists) {
            createPath(folderPath, filePath);
        }
        parseTxtFile(filePath);
    }

    private void createPath(Path folderPath, Path filePath) {
        File file = new File(String.valueOf(folderPath));
        try{
            if(file.mkdir()) {
                Path newFilePath = Paths.get(String.valueOf(filePath));
                Files.createFile(newFilePath);
            } else {
                throw new SaveFileNotCreatedException();
            }
        } catch (SaveFileNotCreatedException | IOException e) {
            System.out.println("Save file was not created");
        }
    }

    private void parseTxtFile(Path filePath) {
        try(Scanner inputFileScanner = new Scanner(new File(String.valueOf(filePath)))) {
            while (inputFileScanner.hasNextLine()) {
                String line = inputFileScanner.nextLine();
                String[] taskAttributes = line.split(" \\| ");
                String taskType = taskAttributes[0];
                switch (taskType) {
                case "T":
                    tasks.add(new Todo(Boolean.parseBoolean(taskAttributes[1]), taskAttributes[2]));
                    break;
                case "D":
                    tasks.add(new Deadline(Boolean.parseBoolean(taskAttributes[1]), taskAttributes[2],
                            taskAttributes[3]));
                    break;
                case "E":
                    tasks.add(new Event(Boolean.parseBoolean(taskAttributes[1]), taskAttributes[2],
                            taskAttributes[3]));
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found");
        }
    }
}
