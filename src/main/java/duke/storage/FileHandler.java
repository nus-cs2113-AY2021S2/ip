package duke.storage;

import duke.task.*;
import duke.taskList.TaskList;
import duke.ui.UI;

import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_PATH = "data/";
    private static final String FILE_NAME = "tasks.txt";
    private static final String DELIMINATOR = "#";
    private static final String PADDING_TIME = "NO_TIME_LIMIT";

    private static void writeFile(String content) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH + FileHandler.FILE_NAME);
        fw.write(content);
        fw.close();
    }

    private static ArrayList<Task> getTasksFromText(File taskFile) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(taskFile);
        while (sc.hasNext()) {
            String taskStr = sc.nextLine();
            Task newTask = convertStringToTaskObject(taskStr);
            if (newTask != null) {
                tasks.add(newTask);
            }
        }
        return tasks;
    }

    private static Task convertStringToTaskObject(String taskStr) {
        String[] taskWords = taskStr.split(DELIMINATOR);
        String type = taskWords[0];
        boolean isDone = Boolean.parseBoolean(taskWords[1]);
        String content = taskWords[2];
        LocalDate time = null;
        if (!taskWords[3].equals(PADDING_TIME)){
             time = LocalDate.parse(taskWords[3]);
        }
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
            localTasks = getTasksFromText(taskFile);
        } catch (FileNotFoundException e) {
            System.out.println(UI.DIVIDER +
                    " Local task list file is not found.\n A new task list is initialized for you.");
            createDataDir();
        }
        return localTasks;
    }

    public static void writeTaskList(TaskList taskListToWrite) {
        ArrayList<Task> tasks = taskListToWrite.getTasks();
        String taskInText = convertTaskToText(tasks);
        try {
            writeFile(taskInText);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(UI.DIVIDER + e.getMessage() + "\n" + UI.DIVIDER_LINE_ONLY);
        }
    }

    private static String convertTaskToText(ArrayList<Task> tasks) {
        StringBuilder taskInText = new StringBuilder();
        for (Task task : tasks) {
            TaskType type = task.getTaskType();
            String timeLimit;
            if (type.equals(TaskType.TODO)) {
                timeLimit = PADDING_TIME;
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
