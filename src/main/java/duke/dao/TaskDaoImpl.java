package duke.dao;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskDaoImpl implements TaskDao {
    private static Path taskDataPath = Paths.get(System.getProperty("user.dir"), "data", "task.txt");
    private static File taskFile = new File(String.valueOf(taskDataPath));

    public TaskDaoImpl() {
        try {
            taskFile.getParentFile().mkdirs();
            taskFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Task> loadAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                char taskType = line.charAt(1);
                boolean isDone = (String.valueOf(line.charAt(4)).compareTo("\u2718") == 0) ? true : false;
                String commandArgs = line.substring(7);
                Task task;

                switch (taskType) {
                case 'T':
                    task = new Todo(commandArgs);
                    if (isDone) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                    break;
                case 'D':
                    String[] deadlineArgs = commandArgs.split("\\s+\\(by:\\s+", 2);
                    task = new Deadline(deadlineArgs[0], deadlineArgs[1].substring(0, deadlineArgs[1].length() - 1));
                    if (isDone) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                    break;
                case 'E':
                    String[] eventArgs = commandArgs.split("\\s+\\(at:\\s+", 2);
                    task = new Event(eventArgs[0], eventArgs[1].substring(0, eventArgs[1].length() - 1));
                    if (isDone) {
                        task.setDone(true);
                    }
                    tasks.add(task);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    @Override
    public void saveAllTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(taskFile, false);
            for (Task task : tasks) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
