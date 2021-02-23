package duke.storage;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads tasks from duke.txt or creates duke.txt if it doesn't exist
 * and saves tasks to duke.txt.
 */
public class Storage {

    /**
     * Saves tasks to duke.txt.
     *
     * @param taskList List of current tasks.
     */
    public void saveToFile(TaskList taskList) {
        String filePath = new File("").getAbsolutePath();

        try {
            FileWriter fw = new FileWriter(filePath + "/duke.txt");
            ArrayList<Task> tasks = taskList.getTaskList();
            for (Task task: tasks) {
                String taskDoneStatus = task.getStatus().trim();
                String line = "";
                switch (task.getTaskType()) {
                case "todo":
                    line = "todo" + "||" + taskDoneStatus + "||" + task.getDescription();
                    break;
                case "deadline":
                    Deadline deadline = (Deadline) task;
                    line = "deadline" + "||" + taskDoneStatus + "||" + task.getDescription() + "||" + deadline.getBy();
                    break;
                case "event":
                    Event event = (Event) task;
                    line = "event" + "||" + taskDoneStatus + "||" + task.getDescription() + "||" + event.getAt();
                    break;
                }
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
                System.out.println("ERROR: something went wrong! :(");
        }
    }

    /**
     * Loads tasks from duke.txt.
     *
     * @param filePath File path of duke.txt.
     * @param taskList List of tasks in duke.txt
     * @throws FileNotFoundException If duke.txt does not exist.
     */
    public void loadFromFile(String filePath, TaskList taskList) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        ArrayList<Task> tasks = taskList.getTaskList();

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskDetails = line.split("\\|\\|");
            String doneStatus = taskDetails[1];
            switch (taskDetails[0]) {
            case "todo":
                ToDo todo = new ToDo(taskDetails[2]);
                if (doneStatus.equals("[X]")) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "deadline":
                Deadline deadline = new Deadline(taskDetails[2], taskDetails[3]);
                if (doneStatus.equals("[X]")) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "event":
                Event event = new Event(taskDetails[2], taskDetails[3]);
                if (doneStatus.equals("[X]")) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            }
        }

    }

}
