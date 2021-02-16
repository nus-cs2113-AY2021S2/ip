package duke.fileHandling;

import duke.Duke;
import duke.task.*;

import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_PATH = "data/";
    private static final String FILE_NAME = "tasks.txt";
    private static final String DELIMINATOR = "#";

    private static void writeFile(String fileName, String content) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH + fileName);
        fw.write(content);
        fw.close();
    }

    private static ArrayList<Task> parseTasks(File taskFile) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(taskFile);
        while (sc.hasNext()) {
            String taskStr = sc.nextLine();
            Task newTask = parseTaskObject(taskStr);
            if (newTask != null) {
                tasks.add(newTask);
            }
        }
        return tasks;
    }

    private static Task parseTaskObject(String taskStr) {
        String[] taskWords = taskStr.split(DELIMINATOR);
        String type = taskWords[0];
        boolean isDone = Boolean.parseBoolean(taskWords[1]);
        String content = taskWords[2];
        String time = taskWords[3];
        Task newTask;
        switch (type) {
        case "TODO":
            newTask = new Todo(content);
            break;
        case "EVENT":
            newTask = new Event(content, time);
            break;
        case "DEADLINE":
            newTask = new Deadline(content, time);
            break;
        default:
            System.out.println("Task type not identified!\n");
            return null;
        }
        newTask.setDone(isDone);
        return newTask;
    }

    private static void createDataDir() {
        try {
            Files.createDirectory(Path.of(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Task> readTasksFromFile() {
        File taskFile = new File(FILE_PATH + FILE_NAME);
        ArrayList<Task> localTasks = null;
        try {
            localTasks = parseTasks(taskFile);
        } catch (FileNotFoundException e) {
            System.out.println(Duke.DIVIDER +
                    " Local task list file is not found.\n A new task list is initialized for you.");
            createDataDir();
        }
        return localTasks;
    }

    public static void writeTaskList(TaskList taskListToWrite) {
        ArrayList<Task> tasks = taskListToWrite.getTasks();
        String taskInText = convertTaskToText(tasks);
        try {
            writeFile(FILE_NAME, taskInText);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(Duke.DIVIDER + e.getMessage() + "\n" + Duke.DIVIDER_LINE_ONLY);
        }
    }

    private static String convertTaskToText(ArrayList<Task> tasks) {
        StringBuilder taskInText = new StringBuilder();
        for (Task task : tasks) {
            TaskType type = task.getTaskType();
            String timeLimit;
            if (type.equals(TaskType.TODO)) {
                timeLimit = "NO_TIME_LIMIT";
            } else {
                timeLimit = task.getTimeLimitString();
            }
            String taskLine = task.getTaskType()
                    + DELIMINATOR + task.isDone()
                    + DELIMINATOR + task.getTaskContent()
                    + DELIMINATOR + timeLimit;
            taskInText.append(taskLine).append(System.lineSeparator());
        }
        return taskInText.toString();
    }
}
