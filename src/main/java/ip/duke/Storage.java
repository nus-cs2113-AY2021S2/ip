package ip.duke;

import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Represents an object that deals with loading information from a file
 * as well as writing information into it
 * A Storage(filepath) object corresponds to an object that can read and write
 * into the file at the path given by filepath
 */
public class Storage {
    public static String filePath;
    public static ArrayList<Task> list = new ArrayList<>();

    /**
     * Constructs a Storage object to access the data in the given address.
     *
     * @param filePath the String given to find the path of the file.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads the data stored in the given file address and re-formats them into a task list.
     * If the file does not exist, create a new file to store the task information
     * The stored data may contain three types todo, deadline, and event with their corresponding task status.
     * no parameters
     *
     * @return the task list that read from that file
     * @throws IOException an exception occurs when encountered with problems reading into the file
     */
    public static ArrayList<Task> loadData() throws IOException {
        File dataFile = new File(filePath);
        if (dataFile.createNewFile()) {
            Ui.printLine();
            System.out.println("Since the file does not exist, I have created a new one.");
            Ui.printLine();
        }
        Scanner dataScanner = new Scanner(dataFile);
        while (dataScanner.hasNext()) {
            String data = dataScanner.nextLine();
            String type = data.substring(0, 1);
            boolean isDone = data.charAt(4) == '1';
            String content = data.substring(8);
            String description = content;
            String byTime;
            String atTime;
            int separatePoint = content.length() - 1;
            if (content.contains("|")) {
                separatePoint = content.indexOf("|");
                description = content.substring(0, separatePoint - 1);
            }
            switch (type) {
            case "T":
                fileUpdateTodo(list, description, isDone);
                break;
            case "D":
                byTime = content.substring(separatePoint + 2);
                fileUpdateDeadline(list, description, byTime, isDone);
                break;
            case "E":
                atTime = content.substring(separatePoint + 2);
                fileUpdateEvent(list, description, atTime, isDone);
                break;
            default:
                break;
            }
        }
        return list;
    }

    /**
     * Update a todo type task read from the file to the task list.
     *
     * @param list        the list that stores all the tasks
     * @param description the string that describe this todo task
     * @param isDone      the status of this todo tasks
     */
    public static void fileUpdateTodo(ArrayList<Task> list, String description, boolean isDone) {
        list.add(new Todo(description));
        list.get(list.size() - 1).setDone(isDone);
    }

    /**
     * Update a deadline type task read from the file to the task list.
     *
     * @param list        the list that stores all the tasks
     * @param description the string that describe this todo task
     * @param byTime      the string representing the latest time the task should be done
     * @param isDone      the status of this todo tasks
     */
    public static void fileUpdateDeadline(ArrayList<Task> list, String description, String byTime, boolean isDone) {
        list.add(new Deadline(description, byTime));
        list.get(list.size() - 1).setDone(isDone);
    }

    /**
     * Update an event type task read from the file to the task list.
     *
     * @param list        the list that stores all the tasks
     * @param description the string that describe this todo task
     * @param atTime      the string representing the time the task should start
     * @param isDone      the status of this todo tasks
     */
    public static void fileUpdateEvent(ArrayList<Task> list, String description, String atTime, boolean isDone) {
        list.add(new Event(description, atTime));
        list.get(list.size() - 1).setDone(isDone);
    }

    /**
     * Save the updated task list information as data format
     * back to the given file address whenever the task list changes.
     * returns nothing
     * if the ArrayList is empty, nothing will be written
     *
     * @throws IOException an exception occurs when encountered with problems writing to the file
     */
    public static void saveData() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < TaskList.getSize(); i++) {
            fw.write(TaskList.getList().get(i).toDataString() + "\n");
        }
        fw.close();
    }
}
